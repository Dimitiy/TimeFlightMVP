
package com.shiz.flighttime.database;

import java.util.Date;
import android.os.Parcelable;
import android.os.Parcelable.Creator;
import org.parceler.Generated;
import org.parceler.IdentityCollection;
import org.parceler.ParcelWrapper;
import org.parceler.ParcelerRuntimeException;

@Generated(value = "org.parceler.ParcelAnnotationProcessor", date = "2016-09-09T16:40+0300")
@SuppressWarnings({
    "unchecked",
    "deprecation"
})
public class FlightDB$$Parcelable
    implements Parcelable, ParcelWrapper<com.shiz.flighttime.database.FlightDB>
{

    private com.shiz.flighttime.database.FlightDB flightDB$$1;
    @SuppressWarnings("UnusedDeclaration")
    public final static FlightDB$$Parcelable.Creator$$1 CREATOR = new FlightDB$$Parcelable.Creator$$1();

    public FlightDB$$Parcelable(com.shiz.flighttime.database.FlightDB flightDB$$3) {
        flightDB$$1 = flightDB$$3;
    }

    @Override
    public void writeToParcel(android.os.Parcel parcel$$0, int flags) {
        write(flightDB$$1, parcel$$0, flags, new IdentityCollection());
    }

    public static void write(com.shiz.flighttime.database.FlightDB flightDB$$2, android.os.Parcel parcel$$1, int flags$$0, IdentityCollection identityMap$$0) {
        int identity$$0 = identityMap$$0 .getKey(flightDB$$2);
        if (identity$$0 != -1) {
            parcel$$1 .writeInt(identity$$0);
        } else {
            parcel$$1 .writeInt(identityMap$$0 .put(flightDB$$2));
            parcel$$1 .writeLong(flightDB$$2 .getDuration());
            parcel$$1 .writeSerializable(flightDB$$2 .getDate());
            parcel$$1 .writeInt(flightDB$$2 .getId());
            parcel$$1 .writeInt((flightDB$$2 .isDay()? 1 : 0));
        }
    }

    @Override
    public int describeContents() {
        return  0;
    }

    @Override
    public com.shiz.flighttime.database.FlightDB getParcel() {
        return flightDB$$1;
    }

    public static com.shiz.flighttime.database.FlightDB read(android.os.Parcel parcel$$3, IdentityCollection identityMap$$1) {
        int identity$$1 = parcel$$3 .readInt();
        if (identityMap$$1 .containsKey(identity$$1)) {
            if (identityMap$$1 .isReserved(identity$$1)) {
                throw new ParcelerRuntimeException("An instance loop was detected whild building Parcelable and deseralization cannot continue.  This error is most likely due to using @ParcelConstructor or @ParcelFactory.");
            }
            return identityMap$$1 .get(identity$$1);
        } else {
            com.shiz.flighttime.database.FlightDB flightDB$$4;
            int reservation$$0 = identityMap$$1 .reserve();
            flightDB$$4 = new com.shiz.flighttime.database.FlightDB();
            identityMap$$1 .put(reservation$$0, flightDB$$4);
            flightDB$$4 .setDuration(parcel$$3 .readLong());
            flightDB$$4 .setDate(((Date) parcel$$3 .readSerializable()));
            flightDB$$4 .setId(parcel$$3 .readInt());
            flightDB$$4 .setDay((parcel$$3 .readInt() == 1));
            return flightDB$$4;
        }
    }

    public final static class Creator$$1
        implements Creator<FlightDB$$Parcelable>
    {


        @Override
        public FlightDB$$Parcelable createFromParcel(android.os.Parcel parcel$$2) {
            return new FlightDB$$Parcelable(read(parcel$$2, new IdentityCollection()));
        }

        @Override
        public FlightDB$$Parcelable[] newArray(int size) {
            return new FlightDB$$Parcelable[size] ;
        }

    }

}
