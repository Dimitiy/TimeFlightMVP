
package com.shiz.flighttime.data;

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
public class YearEntity$$Parcelable
    implements Parcelable, ParcelWrapper<com.shiz.flighttime.data.YearEntity>
{

    private com.shiz.flighttime.data.YearEntity yearEntity$$0;
    @SuppressWarnings("UnusedDeclaration")
    public final static YearEntity$$Parcelable.Creator$$0 CREATOR = new YearEntity$$Parcelable.Creator$$0();

    public YearEntity$$Parcelable(com.shiz.flighttime.data.YearEntity yearEntity$$2) {
        yearEntity$$0 = yearEntity$$2;
    }

    @Override
    public void writeToParcel(android.os.Parcel parcel$$0, int flags) {
        write(yearEntity$$0, parcel$$0, flags, new IdentityCollection());
    }

    public static void write(com.shiz.flighttime.data.YearEntity yearEntity$$1, android.os.Parcel parcel$$1, int flags$$0, IdentityCollection identityMap$$0) {
        int identity$$0 = identityMap$$0 .getKey(yearEntity$$1);
        if (identity$$0 != -1) {
            parcel$$1 .writeInt(identity$$0);
        } else {
            parcel$$1 .writeInt(identityMap$$0 .put(yearEntity$$1));
            parcel$$1 .writeLong(yearEntity$$1 .countHoursInYear);
            parcel$$1 .writeLong(yearEntity$$1 .countDayHours);
            parcel$$1 .writeLong(yearEntity$$1 .countNightHours);
            parcel$$1 .writeString(yearEntity$$1 .years);
        }
    }

    @Override
    public int describeContents() {
        return  0;
    }

    @Override
    public com.shiz.flighttime.data.YearEntity getParcel() {
        return yearEntity$$0;
    }

    public static com.shiz.flighttime.data.YearEntity read(android.os.Parcel parcel$$3, IdentityCollection identityMap$$1) {
        int identity$$1 = parcel$$3 .readInt();
        if (identityMap$$1 .containsKey(identity$$1)) {
            if (identityMap$$1 .isReserved(identity$$1)) {
                throw new ParcelerRuntimeException("An instance loop was detected whild building Parcelable and deseralization cannot continue.  This error is most likely due to using @ParcelConstructor or @ParcelFactory.");
            }
            return identityMap$$1 .get(identity$$1);
        } else {
            com.shiz.flighttime.data.YearEntity yearEntity$$3;
            int reservation$$0 = identityMap$$1 .reserve();
            yearEntity$$3 = new com.shiz.flighttime.data.YearEntity();
            identityMap$$1 .put(reservation$$0, yearEntity$$3);
            yearEntity$$3 .countHoursInYear = parcel$$3 .readLong();
            yearEntity$$3 .countDayHours = parcel$$3 .readLong();
            yearEntity$$3 .countNightHours = parcel$$3 .readLong();
            yearEntity$$3 .years = parcel$$3 .readString();
            return yearEntity$$3;
        }
    }

    public final static class Creator$$0
        implements Creator<YearEntity$$Parcelable>
    {


        @Override
        public YearEntity$$Parcelable createFromParcel(android.os.Parcel parcel$$2) {
            return new YearEntity$$Parcelable(read(parcel$$2, new IdentityCollection()));
        }

        @Override
        public YearEntity$$Parcelable[] newArray(int size) {
            return new YearEntity$$Parcelable[size] ;
        }

    }

}
