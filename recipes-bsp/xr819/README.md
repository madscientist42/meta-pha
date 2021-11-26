# xradio-experimental

This is an ***experimental*** driver for the Allwinner/XRadioTech XR819 SDIO WiFi chip as found on things like the Orange PI Zero, etc.  It was originated by
the Armbian project as a clean-up of the original derived driver in Allwinner's SDK that showed up at the time that this part showed up on the scene.  This
is pulled from the actively maintained fork of the same on GitHub sponsored by the user fifteenhex.


# READ THIS

This driver _just_ about works. If it loads and starts working normally it usually doesn't have any major issues until you try to reboot. The structure of the driver isn't great and it can lock up the kernel if it gets confused. It's not production ready for your large scale commercial IoT deployment.  With the latest version
from roughly two years (as of 11/25/21) ago, reports are favorable towards it being relatively "solid" with proper thermal management and letting it run in low-power
mode from these mods all the time.  It's suggested that you heatsink/heatspreader the WiFi part as it is likely to help with stability on this part.

Also: The xr819 chip/firmware is reported to drop tons and tons of frames with FCS errors and this makes performance horrible at best on this chip.  But then,
it's an IoT part at best- if you wanted barn burning performance on such low-spec devices, you'd be using other silicon from start to finish.

***Most*** people have lost interest in having anything to do with the xr819 because of a lot of demanding people that don't understand that there's a LOT of
intrinsically nigh-unfixable things and that the people they're demanding fix it didn't get any of that $8 or so they paid for the whole board that it's on...
and that without at least packet dumps or similar, or even a _solid_ programmer's reference guide, there's going to be a long period of time as someone RE's
the silly thing...if even that much.  You get what you pay for or actually ***help*** with, folks.  In this case, PHA Technologies (***waves hand***) is only
providing this as a _courtesy_ to people using a device such as the OrangePI Zero or similar that are using our metadata layer here.  I have HW for right now,
but support for this is going to be a catch-as-catch-can type affair as I've a lot of fish to fry, I'm but one gent working on it, and I'm not getting paid
for any of it at this time.


# How to use this

Build and package this recipe in your Yocto build as part of the image spec.

You will then need to specify one or two regulators for the xr819's 1.8v and 3.3v supplies in your device tree for it
to work right here.  (for example, at: `.../linux_sources/linux-4.12-rc5/arch/arm/boot/dts/sun8i-h2-plus-orangepi-zero.dts`

The orange pi zero only has control over the 1.8v regulator and a 3.3v fixed regulator is provided elsewhere
so we only need one here:

```
vdd_wifi: vdd_wifi {
	compatible = "regulator-fixed";
	regulator-name = "wifi";
	regulator-min-microvolt = <1800000>;
	regulator-max-microvolt = <1800000>;
	gpio = <&pio 0 20 GPIO_ACTIVE_HIGH>;
	startup-delay-us = <70000>;
	enable-active-high;
};
```

If your board you're speccing this out for has both regulators, you'll need to specify it a bit differently
to account for both rails.

Next you need a pwrseq node that controls the reset pin of the xr819.

```
pwrseq_wifi: pwrseq_wifi@0 {
	compatible = "mmc-pwrseq-simple";
	pinctrl-names = "default";
	pinctrl-0 = <&wifi_rst>;
	reset-gpios = <&r_pio 0 7 GPIO_ACTIVE_LOW>;
	post-power-on-delay-ms = <50>;
};
```

Next you need to add some things to the mmc node that the xr819 is connected to.

```
&mmc1 {
	pinctrl-names = "default";
	pinctrl-0 = <&mmc1_pins_a>;
	vqmmc-supply = <&vdd_wifi>;
	vmmc-supply = <&reg_vcc3v3>;
	bus-width = <4>;
	mmc-pwrseq = <&pwrseq_wifi>;
	non-removable;
	status = "okay";

        xr819wifi: xr819wifi@1 {
                reg = <1>;
                compatible = "xradio,xr819";
                pinctrl-names = "default";
                pinctrl-0 = <&wifi_wake>;
                interrupt-parent = <&pio>;
                interrupts = <6 10 IRQ_TYPE_EDGE_RISING>;
                interrupt-names = "host-wake";
                local-mac-address = [dc 44 6d c0 ff ee];
        };
};
```

and probably we also need this:

```
&pio {
	wifi_wake: wifi_wake {
		pins = "PG10";
		function = "gpio_in";
    };
};

```

```
&r_pio {
	wifi_rst: wifi_rst {
		pins = "PL7";
		function = "gpio_out";
    };
};

```

example `sun8i-h2-plus-orangepi-zero.dts` is provided too.

vqmmc-supply and vmmc-supply should reference the regulators that control the xr819 supplies.
The device tree for the SoC the orange pi zero is based on supplies a fixed 3.3v regulator
so we use that for vmmc-supply and provide the 1.8v controllable regulator as vqmmc-supply.
vqmcc-supply is apparently for the IO supply which is 3.3v for the orange pi zero but
swapping vqmmc and vmmc around results in the kernel complaining that the card's (the xr819)
required IO voltage isn't supported. The setup above might not be technically correct but
does work.

The xr819 node should be self explanatory. The compatible string is used by the driver
to find the node. The wake interrupt from the xr819 needs to be provided.

Finally you can specify a MAC address to use. If you don't set one you will get a random one
on each boot. Instead of creating a new device tree file for every system you should
probably overwrite the address given after loading the device tree in u-boot. For the sunxi
uboot all you have to actually do is add something like "ethernet1 = &xr819wifi;" to the
aliases section of the device tree you give to the kernel and u-boot will update the mac
address to something based on the unique chip id for you.


# Running

This should be obvious.  If you've got the above DT specifications done right, it should
be "lit" and ready to go.  Firmware and driver are appropriately taken care of for you
as a part of this build/packaging using this specific recipe.


# What works

Working:

Standard client station mode seems to work fine.
Master (AP) mode works with WPA/WPA2 enabled etc.
Dual role station and master mode.

Anything else should be deemed to be, "not working," or, "blind dumb luck," as it's not a foregone.
Try it and see.  If it blows up or doesn't work stably, that's along the lines of the old joke:
"Doc?  It hurts when I do this..."  "Well, then, DON'T DO IT!"  I will update this list as I experiment
with things outside of this (For example, "Can it dual role two STAs and run one as a BATMAN mesh node?"
"Dunno...don't think anyone's tried...mainly because it's been so craptacular over time...")


# Issues

The firmware running on the xr819 is very crash happy and the driver is a bit
stupid. For example the driver can get confused about how many packets of data
the xr819 has for it to read and can try to read too many. The firmware on the
xr819 responds by triggering an assert and shutting down. The driver gets
a packet that tells it that the firmware is dead and shuts down the thread used
to push and pull data but the rest of the driver and the os has no idea and
if the os tries to interact with the driver everything starts to lock up.

Pings from the device to the network are faster than from the network to the device.
This seems to be because of latency between the interrupt and servicing RX reports
from the device.

