
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
public class MissionDB$$Parcelable
    implements Parcelable, ParcelWrapper<com.shiz.flighttime.database.MissionDB>
{

    private com.shiz.flighttime.database.MissionDB missionDB$$1;
    @SuppressWarnings("UnusedDeclaration")
    public final static MissionDB$$Parcelable.Creator$$2 CREATOR = new MissionDB$$Parcelable.Creator$$2();

    public MissionDB$$Parcelable(com.shiz.flighttime.database.MissionDB missionDB$$3) {
        missionDB$$1 = missionDB$$3;
    }

    @Override
    public void writeToParcel(android.os.Parcel parcel$$0, int flags) {
        write(missionDB$$1, parcel$$0, flags, new IdentityCollection());
    }

    public static void write(com.shiz.flighttime.database.MissionDB missionDB$$2, android.os.Parcel parcel$$1, int flags$$0, IdentityCollection identityMap$$0) {
        int identity$$0 = identityMap$$0 .getKey(missionDB$$2);
        if (identity$$0 != -1) {
            parcel$$1 .writeInt(identity$$0);
        } else {
            parcel$$1 .writeInt(identityMap$$0 .put(missionDB$$2));
            parcel$$1 .writeLong(missionDB$$2 .getDayDuration());
            parcel$$1 .writeLong(missionDB$$2 .getDuration());
            parcel$$1 .writeSerializable(missionDB$$2 .getDate());
            parcel$$1 .writeString(missionDB$$2 .getCity());
            new com.shiz.flighttime.database.FlightDBListParcelConverter().toParcel(missionDB$$2 .getFlightDBRealmList(), parcel$$1);
            parcel$$1 .writeLong(missionDB$$2 .getNightDuration());
            parcel$$1 .writeInt(missionDB$$2 .getId());
        }
    }

    @Override
    public int describeContents() {
        return  0;
    }

    @Override
    public com.shiz.flighttime.database.MissionDB getParcel() {
        return missionDB$$1;
    }

    public static com.shiz.flighttime.database.MissionDB read(android.os.Parcel parcel$$3, IdentityCollection identityMap$$1) {
        int identity$$1 = parcel$$3 .readInt();
        if (identityMap$$1 .containsKey(identity$$1)) {
            if (identityMap$$1 .isReserved(identity$$1)) {
                throw new ParcelerRuntimeException("An instance loop was detected whild building Parcelable and deseralization cannot continue.  This error is most likely due to using @ParcelConstructor or @ParcelFactory.");
            }
            return identityMap$$1 .get(identity$$1);
        } else {
            com.shiz.flighttime.database.MissionDB missionDB$$4;
            int reservation$$0 = identityMap$$1 .reserve();
            missionDB$$4 = new com.shiz.flighttime.database.MissionDB();
            identityMap$$1 .put(reservation$$0, missionDB$$4);
            missionDB$$4 .setDayDuration(parcel$$3 .readLong());
            missionDB$$4 .setDuration(parcel$$3 .readLong());
            missionDB$$4 .setDate(((Date) parcel$$3 .readSerializable()));
            missionDB$$4 .setCity(parcel$$3 .readString());
            missionDB$$4 .setFlightDBRealmList(new com.shiz.flighttime.database.FlightDBListParcelConverter().fromParcel(parcel$$3));
            missionDB$$4 .setNightDuration(parcel$$3 .readLong());
            missionDB$$4 .setId(parcel$$3 .readInt());
            return missionDB$$4;
        }
    }

    public final static class Creator$$2
        implements Creator<MissionDB$$Parcelable>
    {


        @Override
        public MissionDB$$Parcelable createFromParcel(android.os.Parcel parcel$$2) {
            return new MissionDB$$Parcelable(read(parcel$$2, new IdentityCollection()));
        }

        @Override
        public MissionDB$$Parcelable[] newArray(int size) {
            return new MissionDB$$Parcelable[size] ;
        }

    }

}
