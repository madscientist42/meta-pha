#! /bin/sh
#
# motion 4.2.1+git20181209-7bd179c	
# Start the motion detection .
#

NAME=motion
PATH=/bin:/usr/bin:/sbin:/usr/sbin
DAEMON=/usr/bin/motion
PIDFILE=/var/run/motion/$NAME.pid


export LANG=C
export PATH


case "$1" in
  start)
    echo "Starting motion detection : $NAME"
    start-stop-daemon --start --pidfile $PIDFILE --exec $DAEMON --chuid motion
    ;;

  stop)
    echo "Stopping motion detection : $NAME"
    start-stop-daemon --stop --pidfile $PIDFILE --oknodo --exec $DAEMON --retry 30 
    ;;

  status)
    echo "Status motion detection : $NAME"
    if (test -f $PIDFILE); then
        echo -n "Running process for $NAME : "
    	pidof $NAME  
    else
    	echo "Stopped"
    fi
    ;;  
    
  reload-config)
    echo "Reloading $NAME configuration"
    start-stop-daemon --stop --pidfile $PIDFILE --signal HUP --exec $DAEMON
    ;;

  restart-motion)
    echo "Restarting $NAME"
    start-stop-daemon --stop --pidfile $PIDFILE --oknodo --exec $DAEMON  --retry 30
    start-stop-daemon --start --pidfile $PIDFILE --exec $DAEMON --chuid motion
    ;;

  restart)
    $0 restart-motion
    exit $?
    ;;

  *)
    echo "Usage: /etc/init.d/$NAME {start|stop|status|reload-config|restart}"
    exit 1
    ;;
esac

if [ $? == 0 ]; then
	echo .
	exit 0
else
	echo failed
	exit 1
fi
