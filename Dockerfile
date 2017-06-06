FROM websphere-liberty:webProfile7

COPY target/bike.war /config/dropins/bike.war
