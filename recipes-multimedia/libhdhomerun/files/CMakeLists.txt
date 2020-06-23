CMAKE_MINIMUM_REQUIRED (VERSION 2.8.8)
PROJECT (libhdhomerun C)

add_library(
    hdhomerun SHARED
    hdhomerun_channels.c     
    hdhomerun_debug.c            
    hdhomerun_os_posix.c    
    hdhomerun_channelscan.c  
    hdhomerun_device.c           
    hdhomerun_video.c
    hdhomerun_config.c       
    hdhomerun_device_selector.c  
    hdhomerun_pkt.c
    hdhomerun_control.c      
    hdhomerun_discover.c         
    hdhomerun_sock_posix.c
)

INSTALL(TARGETS hdhomerun RUNTIME DESTINATION ${CMAKE_INSTALL_LIBDIR})
FILE(GLOB LIBHDHOMERUN_INCL *.h)
INSTALL(FILES ${LIBHDHOMERUN_INCL} DESTINATION ${CMAKE_INSTALL_PREFIX}/include/libhdhomerun)

