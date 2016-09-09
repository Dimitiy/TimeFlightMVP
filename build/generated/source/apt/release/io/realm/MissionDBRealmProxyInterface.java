package io.realm;


public interface MissionDBRealmProxyInterface {
    public String realmGet$city();
    public void realmSet$city(String value);
    public int realmGet$id();
    public void realmSet$id(int value);
    public java.util.Date realmGet$date();
    public void realmSet$date(java.util.Date value);
    public long realmGet$duration();
    public void realmSet$duration(long value);
    public long realmGet$dayDuration();
    public void realmSet$dayDuration(long value);
    public long realmGet$nightDuration();
    public void realmSet$nightDuration(long value);
    public RealmList<com.shiz.flighttime.database.FlightDB> realmGet$flightDBRealmList();
    public void realmSet$flightDBRealmList(RealmList<com.shiz.flighttime.database.FlightDB> value);
}
