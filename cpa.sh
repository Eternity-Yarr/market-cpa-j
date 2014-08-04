#!/bin/bash
logfile=/var/log/market-cpa.log
pidfile=/var/run/market-cpa.pid
cd /srv/m
now="$(date +'%d-%m-%Y %T')"

case "$1" in

start)  echo "Starting!"
        printf " -++- %s\n" "$now" >> $logfile
        cd /srv/market-cpa
        TZ='Europe/Moscow' nohup /srv/bin/java -cp /srv/market-cpa/lib/*:/srv/market-cpa/classes/ org.lutra.cpa.Main </dev/null >> $logfile 2>&1 &
        pid=$!
        echo $! > $pidfile
        echo "Working as $pid"

    ;;
stop) echo "Stoping!"
        start-stop-daemon --stop --pidfile=$pidfile
        rm -f $pidfile
        printf " ---- %s\n" "$now" >> $logfile
    ;;
status)
    printf "$0 is "
    if [ -n "${pidfile:-}" ]; then
     if [ -e "$pidfile" ]; then
      if [ -r "$pidfile" ]; then
        read pid < "$pidfile"
        if [ -n "${pid:-}" ]; then
            if $(kill -0 "${pid:-}" 2> /dev/null); then
                #echo "$pid" || true
                status="RUNNING"
            elif ps "${pid:-}" >/dev/null 2>&1; then
                #echo "$pid" || true
                status="RUNNING"
            else
                status="DEAD, PID EXISTS"
            fi
        fi
      else
        status="UNKNOWN"
      fi
     else
       # pid file doesn't exist, try to find the pid nevertheless
       if [ -x /bin/pidof ] && [ ! "$specified" ]; then
         status="0"
         /bin/pidof -o %PPID -x $1 || status="$?"
         if [ "$status" = 1 ]; then
            status="NOT RUNNING"
         fi
         status="WTF"
       fi
        status="STOPPED"
     fi
    fi
    echo "$status"
    ;;
*)      echo "Usage: $0 {start|status|stop}"
        exit 2
    ;;

esac
exit 0
