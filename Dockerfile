FROM websphere-liberty:webProfile7

COPY target/bike-shop.war /config/dropins/bike-shop.war
