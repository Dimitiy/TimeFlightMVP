package io.realm;


import android.util.JsonReader;
import android.util.JsonToken;
import io.realm.RealmFieldType;
import io.realm.exceptions.RealmMigrationNeededException;
import io.realm.internal.ColumnInfo;
import io.realm.internal.ImplicitTransaction;
import io.realm.internal.LinkView;
import io.realm.internal.RealmObjectProxy;
import io.realm.internal.Table;
import io.realm.internal.TableOrView;
import io.realm.internal.android.JsonUtils;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class FlightDBRealmProxy extends com.shiz.flighttime.database.FlightDB
    implements RealmObjectProxy, FlightDBRealmProxyInterface {

    static final class FlightDBColumnInfo extends ColumnInfo {

        public final long idIndex;
        public final long dateIndex;
        public final long durationIndex;
        public final long dayIndex;

        FlightDBColumnInfo(String path, Table table) {
            final Map<String, Long> indicesMap = new HashMap<String, Long>(4);
            this.idIndex = getValidColumnIndex(path, table, "FlightDB", "id");
            indicesMap.put("id", this.idIndex);

            this.dateIndex = getValidColumnIndex(path, table, "FlightDB", "date");
            indicesMap.put("date", this.dateIndex);

            this.durationIndex = getValidColumnIndex(path, table, "FlightDB", "duration");
            indicesMap.put("duration", this.durationIndex);

            this.dayIndex = getValidColumnIndex(path, table, "FlightDB", "day");
            indicesMap.put("day", this.dayIndex);

            setIndicesMap(indicesMap);
        }
    }

    private final FlightDBColumnInfo columnInfo;
    private final ProxyState proxyState;
    private static final List<String> FIELD_NAMES;
    static {
        List<String> fieldNames = new ArrayList<String>();
        fieldNames.add("id");
        fieldNames.add("date");
        fieldNames.add("duration");
        fieldNames.add("day");
        FIELD_NAMES = Collections.unmodifiableList(fieldNames);
    }

    FlightDBRealmProxy(ColumnInfo columnInfo) {
        this.columnInfo = (FlightDBColumnInfo) columnInfo;
        this.proxyState = new ProxyState(com.shiz.flighttime.database.FlightDB.class, this);
    }

    @SuppressWarnings("cast")
    public int realmGet$id() {
        proxyState.getRealm$realm().checkIfValid();
        return (int) proxyState.getRow$realm().getLong(columnInfo.idIndex);
    }

    public void realmSet$id(int value) {
        proxyState.getRealm$realm().checkIfValid();
        proxyState.getRow$realm().setLong(columnInfo.idIndex, value);
    }

    @SuppressWarnings("cast")
    public Date realmGet$date() {
        proxyState.getRealm$realm().checkIfValid();
        return (java.util.Date) proxyState.getRow$realm().getDate(columnInfo.dateIndex);
    }

    public void realmSet$date(Date value) {
        proxyState.getRealm$realm().checkIfValid();
        if (value == null) {
            throw new IllegalArgumentException("Trying to set non-nullable field 'date' to null.");
        }
        proxyState.getRow$realm().setDate(columnInfo.dateIndex, value);
    }

    @SuppressWarnings("cast")
    public long realmGet$duration() {
        proxyState.getRealm$realm().checkIfValid();
        return (long) proxyState.getRow$realm().getLong(columnInfo.durationIndex);
    }

    public void realmSet$duration(long value) {
        proxyState.getRealm$realm().checkIfValid();
        proxyState.getRow$realm().setLong(columnInfo.durationIndex, value);
    }

    @SuppressWarnings("cast")
    public boolean realmGet$day() {
        proxyState.getRealm$realm().checkIfValid();
        return (boolean) proxyState.getRow$realm().getBoolean(columnInfo.dayIndex);
    }

    public void realmSet$day(boolean value) {
        proxyState.getRealm$realm().checkIfValid();
        proxyState.getRow$realm().setBoolean(columnInfo.dayIndex, value);
    }

    public static Table initTable(ImplicitTransaction transaction) {
        if (!transaction.hasTable("class_FlightDB")) {
            Table table = transaction.getTable("class_FlightDB");
            table.addColumn(RealmFieldType.INTEGER, "id", Table.NOT_NULLABLE);
            table.addColumn(RealmFieldType.DATE, "date", Table.NOT_NULLABLE);
            table.addColumn(RealmFieldType.INTEGER, "duration", Table.NOT_NULLABLE);
            table.addColumn(RealmFieldType.BOOLEAN, "day", Table.NOT_NULLABLE);
            table.addSearchIndex(table.getColumnIndex("id"));
            table.setPrimaryKey("id");
            return table;
        }
        return transaction.getTable("class_FlightDB");
    }

    public static FlightDBColumnInfo validateTable(ImplicitTransaction transaction) {
        if (transaction.hasTable("class_FlightDB")) {
            Table table = transaction.getTable("class_FlightDB");
            if (table.getColumnCount() != 4) {
                throw new RealmMigrationNeededException(transaction.getPath(), "Field count does not match - expected 4 but was " + table.getColumnCount());
            }
            Map<String, RealmFieldType> columnTypes = new HashMap<String, RealmFieldType>();
            for (long i = 0; i < 4; i++) {
                columnTypes.put(table.getColumnName(i), table.getColumnType(i));
            }

            final FlightDBColumnInfo columnInfo = new FlightDBColumnInfo(transaction.getPath(), table);

            if (!columnTypes.containsKey("id")) {
                throw new RealmMigrationNeededException(transaction.getPath(), "Missing field 'id' in existing Realm file. Either remove field or migrate using io.realm.internal.Table.addColumn().");
            }
            if (columnTypes.get("id") != RealmFieldType.INTEGER) {
                throw new RealmMigrationNeededException(transaction.getPath(), "Invalid type 'int' for field 'id' in existing Realm file.");
            }
            if (table.isColumnNullable(columnInfo.idIndex) && table.findFirstNull(columnInfo.idIndex) != TableOrView.NO_MATCH) {
                throw new IllegalStateException("Cannot migrate an object with null value in field 'id'. Either maintain the same type for primary key field 'id', or remove the object with null value before migration.");
            }
            if (table.getPrimaryKey() != table.getColumnIndex("id")) {
                throw new RealmMigrationNeededException(transaction.getPath(), "Primary key not defined for field 'id' in existing Realm file. Add @PrimaryKey.");
            }
            if (!table.hasSearchIndex(table.getColumnIndex("id"))) {
                throw new RealmMigrationNeededException(transaction.getPath(), "Index not defined for field 'id' in existing Realm file. Either set @Index or migrate using io.realm.internal.Table.removeSearchIndex().");
            }
            if (!columnTypes.containsKey("date")) {
                throw new RealmMigrationNeededException(transaction.getPath(), "Missing field 'date' in existing Realm file. Either remove field or migrate using io.realm.internal.Table.addColumn().");
            }
            if (columnTypes.get("date") != RealmFieldType.DATE) {
                throw new RealmMigrationNeededException(transaction.getPath(), "Invalid type 'Date' for field 'date' in existing Realm file.");
            }
            if (table.isColumnNullable(columnInfo.dateIndex)) {
                throw new RealmMigrationNeededException(transaction.getPath(), "Field 'date' does support null values in the existing Realm file. Remove @Required or @PrimaryKey from field 'date' or migrate using RealmObjectSchema.setNullable().");
            }
            if (!columnTypes.containsKey("duration")) {
                throw new RealmMigrationNeededException(transaction.getPath(), "Missing field 'duration' in existing Realm file. Either remove field or migrate using io.realm.internal.Table.addColumn().");
            }
            if (columnTypes.get("duration") != RealmFieldType.INTEGER) {
                throw new RealmMigrationNeededException(transaction.getPath(), "Invalid type 'long' for field 'duration' in existing Realm file.");
            }
            if (table.isColumnNullable(columnInfo.durationIndex)) {
                throw new RealmMigrationNeededException(transaction.getPath(), "Field 'duration' does support null values in the existing Realm file. Use corresponding boxed type for field 'duration' or migrate using RealmObjectSchema.setNullable().");
            }
            if (!columnTypes.containsKey("day")) {
                throw new RealmMigrationNeededException(transaction.getPath(), "Missing field 'day' in existing Realm file. Either remove field or migrate using io.realm.internal.Table.addColumn().");
            }
            if (columnTypes.get("day") != RealmFieldType.BOOLEAN) {
                throw new RealmMigrationNeededException(transaction.getPath(), "Invalid type 'boolean' for field 'day' in existing Realm file.");
            }
            if (table.isColumnNullable(columnInfo.dayIndex)) {
                throw new RealmMigrationNeededException(transaction.getPath(), "Field 'day' does support null values in the existing Realm file. Use corresponding boxed type for field 'day' or migrate using RealmObjectSchema.setNullable().");
            }
            return columnInfo;
        } else {
            throw new RealmMigrationNeededException(transaction.getPath(), "The 'FlightDB' class is missing from the schema for this Realm.");
        }
    }

    public static String getTableName() {
        return "class_FlightDB";
    }

    public static List<String> getFieldNames() {
        return FIELD_NAMES;
    }

    @SuppressWarnings("cast")
    public static com.shiz.flighttime.database.FlightDB createOrUpdateUsingJsonObject(Realm realm, JSONObject json, boolean update)
        throws JSONException {
        com.shiz.flighttime.database.FlightDB obj = null;
        if (update) {
            Table table = realm.getTable(com.shiz.flighttime.database.FlightDB.class);
            long pkColumnIndex = table.getPrimaryKey();
            long rowIndex = TableOrView.NO_MATCH;
            if (!json.isNull("id")) {
                rowIndex = table.findFirstLong(pkColumnIndex, json.getLong("id"));
            }
            if (rowIndex != TableOrView.NO_MATCH) {
                obj = new io.realm.FlightDBRealmProxy(realm.schema.getColumnInfo(com.shiz.flighttime.database.FlightDB.class));
                ((RealmObjectProxy)obj).realmGet$proxyState().setRealm$realm(realm);
                ((RealmObjectProxy)obj).realmGet$proxyState().setRow$realm(table.getUncheckedRow(rowIndex));
            }
        }
        if (obj == null) {
            if (json.has("id")) {
                if (json.isNull("id")) {
                    obj = (io.realm.FlightDBRealmProxy) realm.createObject(com.shiz.flighttime.database.FlightDB.class, null);
                } else {
                    obj = (io.realm.FlightDBRealmProxy) realm.createObject(com.shiz.flighttime.database.FlightDB.class, json.getInt("id"));
                }
            } else {
                obj = (io.realm.FlightDBRealmProxy) realm.createObject(com.shiz.flighttime.database.FlightDB.class);
            }
        }
        if (json.has("id")) {
            if (json.isNull("id")) {
                throw new IllegalArgumentException("Trying to set non-nullable field 'id' to null.");
            } else {
                ((FlightDBRealmProxyInterface) obj).realmSet$id((int) json.getInt("id"));
            }
        }
        if (json.has("date")) {
            if (json.isNull("date")) {
                ((FlightDBRealmProxyInterface) obj).realmSet$date(null);
            } else {
                Object timestamp = json.get("date");
                if (timestamp instanceof String) {
                    ((FlightDBRealmProxyInterface) obj).realmSet$date(JsonUtils.stringToDate((String) timestamp));
                } else {
                    ((FlightDBRealmProxyInterface) obj).realmSet$date(new Date(json.getLong("date")));
                }
            }
        }
        if (json.has("duration")) {
            if (json.isNull("duration")) {
                throw new IllegalArgumentException("Trying to set non-nullable field 'duration' to null.");
            } else {
                ((FlightDBRealmProxyInterface) obj).realmSet$duration((long) json.getLong("duration"));
            }
        }
        if (json.has("day")) {
            if (json.isNull("day")) {
                throw new IllegalArgumentException("Trying to set non-nullable field 'day' to null.");
            } else {
                ((FlightDBRealmProxyInterface) obj).realmSet$day((boolean) json.getBoolean("day"));
            }
        }
        return obj;
    }

    @SuppressWarnings("cast")
    public static com.shiz.flighttime.database.FlightDB createUsingJsonStream(Realm realm, JsonReader reader)
        throws IOException {
        com.shiz.flighttime.database.FlightDB obj = realm.createObject(com.shiz.flighttime.database.FlightDB.class);
        reader.beginObject();
        while (reader.hasNext()) {
            String name = reader.nextName();
            if (name.equals("id")) {
                if (reader.peek() == JsonToken.NULL) {
                    reader.skipValue();
                    throw new IllegalArgumentException("Trying to set non-nullable field 'id' to null.");
                } else {
                    ((FlightDBRealmProxyInterface) obj).realmSet$id((int) reader.nextInt());
                }
            } else if (name.equals("date")) {
                if (reader.peek() == JsonToken.NULL) {
                    reader.skipValue();
                    ((FlightDBRealmProxyInterface) obj).realmSet$date(null);
                } else if (reader.peek() == JsonToken.NUMBER) {
                    long timestamp = reader.nextLong();
                    if (timestamp > -1) {
                        ((FlightDBRealmProxyInterface) obj).realmSet$date(new Date(timestamp));
                    }
                } else {
                    ((FlightDBRealmProxyInterface) obj).realmSet$date(JsonUtils.stringToDate(reader.nextString()));
                }
            } else if (name.equals("duration")) {
                if (reader.peek() == JsonToken.NULL) {
                    reader.skipValue();
                    throw new IllegalArgumentException("Trying to set non-nullable field 'duration' to null.");
                } else {
                    ((FlightDBRealmProxyInterface) obj).realmSet$duration((long) reader.nextLong());
                }
            } else if (name.equals("day")) {
                if (reader.peek() == JsonToken.NULL) {
                    reader.skipValue();
                    throw new IllegalArgumentException("Trying to set non-nullable field 'day' to null.");
                } else {
                    ((FlightDBRealmProxyInterface) obj).realmSet$day((boolean) reader.nextBoolean());
                }
            } else {
                reader.skipValue();
            }
        }
        reader.endObject();
        return obj;
    }

    public static com.shiz.flighttime.database.FlightDB copyOrUpdate(Realm realm, com.shiz.flighttime.database.FlightDB object, boolean update, Map<RealmModel,RealmObjectProxy> cache) {
        if (object instanceof RealmObjectProxy && ((RealmObjectProxy) object).realmGet$proxyState().getRealm$realm() != null && ((RealmObjectProxy) object).realmGet$proxyState().getRealm$realm().threadId != realm.threadId) {
            throw new IllegalArgumentException("Objects which belong to Realm instances in other threads cannot be copied into this Realm instance.");
        }
        if (object instanceof RealmObjectProxy && ((RealmObjectProxy)object).realmGet$proxyState().getRealm$realm() != null && ((RealmObjectProxy)object).realmGet$proxyState().getRealm$realm().getPath().equals(realm.getPath())) {
            return object;
        }
        RealmObjectProxy cachedRealmObject = cache.get(object);
        if (cachedRealmObject != null) {
            return (com.shiz.flighttime.database.FlightDB) cachedRealmObject;
        } else {
            com.shiz.flighttime.database.FlightDB realmObject = null;
            boolean canUpdate = update;
            if (canUpdate) {
                Table table = realm.getTable(com.shiz.flighttime.database.FlightDB.class);
                long pkColumnIndex = table.getPrimaryKey();
                long rowIndex = table.findFirstLong(pkColumnIndex, ((FlightDBRealmProxyInterface) object).realmGet$id());
                if (rowIndex != TableOrView.NO_MATCH) {
                    realmObject = new io.realm.FlightDBRealmProxy(realm.schema.getColumnInfo(com.shiz.flighttime.database.FlightDB.class));
                    ((RealmObjectProxy)realmObject).realmGet$proxyState().setRealm$realm(realm);
                    ((RealmObjectProxy)realmObject).realmGet$proxyState().setRow$realm(table.getUncheckedRow(rowIndex));
                    cache.put(object, (RealmObjectProxy) realmObject);
                } else {
                    canUpdate = false;
                }
            }

            if (canUpdate) {
                return update(realm, realmObject, object, cache);
            } else {
                return copy(realm, object, update, cache);
            }
        }
    }

    public static com.shiz.flighttime.database.FlightDB copy(Realm realm, com.shiz.flighttime.database.FlightDB newObject, boolean update, Map<RealmModel,RealmObjectProxy> cache) {
        RealmObjectProxy cachedRealmObject = cache.get(newObject);
        if (cachedRealmObject != null) {
            return (com.shiz.flighttime.database.FlightDB) cachedRealmObject;
        } else {
            com.shiz.flighttime.database.FlightDB realmObject = realm.createObject(com.shiz.flighttime.database.FlightDB.class, ((FlightDBRealmProxyInterface) newObject).realmGet$id());
            cache.put(newObject, (RealmObjectProxy) realmObject);
            ((FlightDBRealmProxyInterface) realmObject).realmSet$id(((FlightDBRealmProxyInterface) newObject).realmGet$id());
            ((FlightDBRealmProxyInterface) realmObject).realmSet$date(((FlightDBRealmProxyInterface) newObject).realmGet$date());
            ((FlightDBRealmProxyInterface) realmObject).realmSet$duration(((FlightDBRealmProxyInterface) newObject).realmGet$duration());
            ((FlightDBRealmProxyInterface) realmObject).realmSet$day(((FlightDBRealmProxyInterface) newObject).realmGet$day());
            return realmObject;
        }
    }

    public static long insert(Realm realm, com.shiz.flighttime.database.FlightDB object, Map<RealmModel,Long> cache) {
        if (object instanceof RealmObjectProxy && ((RealmObjectProxy)object).realmGet$proxyState().getRealm$realm() != null && ((RealmObjectProxy)object).realmGet$proxyState().getRealm$realm().getPath().equals(realm.getPath())) {
            return ((RealmObjectProxy)object).realmGet$proxyState().getRow$realm().getIndex();
        }
        Table table = realm.getTable(com.shiz.flighttime.database.FlightDB.class);
        long tableNativePtr = table.getNativeTablePointer();
        FlightDBColumnInfo columnInfo = (FlightDBColumnInfo) realm.schema.getColumnInfo(com.shiz.flighttime.database.FlightDB.class);
        long pkColumnIndex = table.getPrimaryKey();
        long rowIndex = TableOrView.NO_MATCH;
        Object primaryKeyValue = ((FlightDBRealmProxyInterface) object).realmGet$id();
        if (primaryKeyValue != null) {
            rowIndex = Table.nativeFindFirstInt(tableNativePtr, pkColumnIndex, ((FlightDBRealmProxyInterface) object).realmGet$id());
        }
        if (rowIndex == TableOrView.NO_MATCH) {
            rowIndex = Table.nativeAddEmptyRow(tableNativePtr, 1);
            if (primaryKeyValue != null) {
                Table.nativeSetLong(tableNativePtr, pkColumnIndex, rowIndex, ((FlightDBRealmProxyInterface) object).realmGet$id());
            }
        } else {
            Table.throwDuplicatePrimaryKeyException(primaryKeyValue);
        }
        cache.put(object, rowIndex);
        java.util.Date realmGet$date = ((FlightDBRealmProxyInterface)object).realmGet$date();
        if (realmGet$date != null) {
            Table.nativeSetTimestamp(tableNativePtr, columnInfo.dateIndex, rowIndex, realmGet$date.getTime());
        }
        Table.nativeSetLong(tableNativePtr, columnInfo.durationIndex, rowIndex, ((FlightDBRealmProxyInterface)object).realmGet$duration());
        Table.nativeSetBoolean(tableNativePtr, columnInfo.dayIndex, rowIndex, ((FlightDBRealmProxyInterface)object).realmGet$day());
        return rowIndex;
    }

    public static void insert(Realm realm, Iterator<? extends RealmModel> objects, Map<RealmModel,Long> cache) {
        Table table = realm.getTable(com.shiz.flighttime.database.FlightDB.class);
        long tableNativePtr = table.getNativeTablePointer();
        FlightDBColumnInfo columnInfo = (FlightDBColumnInfo) realm.schema.getColumnInfo(com.shiz.flighttime.database.FlightDB.class);
        long pkColumnIndex = table.getPrimaryKey();
        com.shiz.flighttime.database.FlightDB object = null;
        while (objects.hasNext()) {
            object = (com.shiz.flighttime.database.FlightDB) objects.next();
            if(!cache.containsKey(object)) {
                if (object instanceof RealmObjectProxy && ((RealmObjectProxy)object).realmGet$proxyState().getRealm$realm() != null && ((RealmObjectProxy)object).realmGet$proxyState().getRealm$realm().getPath().equals(realm.getPath())) {
                    cache.put(object, ((RealmObjectProxy)object).realmGet$proxyState().getRow$realm().getIndex());
                    continue;
                }
                long rowIndex = TableOrView.NO_MATCH;
                Object primaryKeyValue = ((FlightDBRealmProxyInterface) object).realmGet$id();
                if (primaryKeyValue != null) {
                    rowIndex = Table.nativeFindFirstInt(tableNativePtr, pkColumnIndex, ((FlightDBRealmProxyInterface) object).realmGet$id());
                }
                if (rowIndex == TableOrView.NO_MATCH) {
                    rowIndex = Table.nativeAddEmptyRow(tableNativePtr, 1);
                    if (primaryKeyValue != null) {
                        Table.nativeSetLong(tableNativePtr, pkColumnIndex, rowIndex, ((FlightDBRealmProxyInterface) object).realmGet$id());
                    }
                } else {
                    Table.throwDuplicatePrimaryKeyException(primaryKeyValue);
                }
                cache.put(object, rowIndex);
                java.util.Date realmGet$date = ((FlightDBRealmProxyInterface)object).realmGet$date();
                if (realmGet$date != null) {
                    Table.nativeSetTimestamp(tableNativePtr, columnInfo.dateIndex, rowIndex, realmGet$date.getTime());
                }
                Table.nativeSetLong(tableNativePtr, columnInfo.durationIndex, rowIndex, ((FlightDBRealmProxyInterface)object).realmGet$duration());
                Table.nativeSetBoolean(tableNativePtr, columnInfo.dayIndex, rowIndex, ((FlightDBRealmProxyInterface)object).realmGet$day());
            }
        }
    }

    public static long insertOrUpdate(Realm realm, com.shiz.flighttime.database.FlightDB object, Map<RealmModel,Long> cache) {
        if (object instanceof RealmObjectProxy && ((RealmObjectProxy)object).realmGet$proxyState().getRealm$realm() != null && ((RealmObjectProxy)object).realmGet$proxyState().getRealm$realm().getPath().equals(realm.getPath())) {
            return ((RealmObjectProxy)object).realmGet$proxyState().getRow$realm().getIndex();
        }
        Table table = realm.getTable(com.shiz.flighttime.database.FlightDB.class);
        long tableNativePtr = table.getNativeTablePointer();
        FlightDBColumnInfo columnInfo = (FlightDBColumnInfo) realm.schema.getColumnInfo(com.shiz.flighttime.database.FlightDB.class);
        long pkColumnIndex = table.getPrimaryKey();
        long rowIndex = TableOrView.NO_MATCH;
        Object primaryKeyValue = ((FlightDBRealmProxyInterface) object).realmGet$id();
        if (primaryKeyValue != null) {
            rowIndex = Table.nativeFindFirstInt(tableNativePtr, pkColumnIndex, ((FlightDBRealmProxyInterface) object).realmGet$id());
        }
        if (rowIndex == TableOrView.NO_MATCH) {
            rowIndex = Table.nativeAddEmptyRow(tableNativePtr, 1);
            if (primaryKeyValue != null) {
                Table.nativeSetLong(tableNativePtr, pkColumnIndex, rowIndex, ((FlightDBRealmProxyInterface) object).realmGet$id());
            }
        }
        cache.put(object, rowIndex);
        java.util.Date realmGet$date = ((FlightDBRealmProxyInterface)object).realmGet$date();
        if (realmGet$date != null) {
            Table.nativeSetTimestamp(tableNativePtr, columnInfo.dateIndex, rowIndex, realmGet$date.getTime());
        } else {
            Table.nativeSetNull(tableNativePtr, columnInfo.dateIndex, rowIndex);
        }
        Table.nativeSetLong(tableNativePtr, columnInfo.durationIndex, rowIndex, ((FlightDBRealmProxyInterface)object).realmGet$duration());
        Table.nativeSetBoolean(tableNativePtr, columnInfo.dayIndex, rowIndex, ((FlightDBRealmProxyInterface)object).realmGet$day());
        return rowIndex;
    }

    public static void insertOrUpdate(Realm realm, Iterator<? extends RealmModel> objects, Map<RealmModel,Long> cache) {
        Table table = realm.getTable(com.shiz.flighttime.database.FlightDB.class);
        long tableNativePtr = table.getNativeTablePointer();
        FlightDBColumnInfo columnInfo = (FlightDBColumnInfo) realm.schema.getColumnInfo(com.shiz.flighttime.database.FlightDB.class);
        long pkColumnIndex = table.getPrimaryKey();
        com.shiz.flighttime.database.FlightDB object = null;
        while (objects.hasNext()) {
            object = (com.shiz.flighttime.database.FlightDB) objects.next();
            if(!cache.containsKey(object)) {
                if (object instanceof RealmObjectProxy && ((RealmObjectProxy)object).realmGet$proxyState().getRealm$realm() != null && ((RealmObjectProxy)object).realmGet$proxyState().getRealm$realm().getPath().equals(realm.getPath())) {
                    cache.put(object, ((RealmObjectProxy)object).realmGet$proxyState().getRow$realm().getIndex());
                    continue;
                }
                long rowIndex = TableOrView.NO_MATCH;
                Object primaryKeyValue = ((FlightDBRealmProxyInterface) object).realmGet$id();
                if (primaryKeyValue != null) {
                    rowIndex = Table.nativeFindFirstInt(tableNativePtr, pkColumnIndex, ((FlightDBRealmProxyInterface) object).realmGet$id());
                }
                if (rowIndex == TableOrView.NO_MATCH) {
                    rowIndex = Table.nativeAddEmptyRow(tableNativePtr, 1);
                    if (primaryKeyValue != null) {
                        Table.nativeSetLong(tableNativePtr, pkColumnIndex, rowIndex, ((FlightDBRealmProxyInterface) object).realmGet$id());
                    }
                }
                cache.put(object, rowIndex);
                java.util.Date realmGet$date = ((FlightDBRealmProxyInterface)object).realmGet$date();
                if (realmGet$date != null) {
                    Table.nativeSetTimestamp(tableNativePtr, columnInfo.dateIndex, rowIndex, realmGet$date.getTime());
                } else {
                    Table.nativeSetNull(tableNativePtr, columnInfo.dateIndex, rowIndex);
                }
                Table.nativeSetLong(tableNativePtr, columnInfo.durationIndex, rowIndex, ((FlightDBRealmProxyInterface)object).realmGet$duration());
                Table.nativeSetBoolean(tableNativePtr, columnInfo.dayIndex, rowIndex, ((FlightDBRealmProxyInterface)object).realmGet$day());
            }
        }
    }

    public static com.shiz.flighttime.database.FlightDB createDetachedCopy(com.shiz.flighttime.database.FlightDB realmObject, int currentDepth, int maxDepth, Map<RealmModel, CacheData<RealmModel>> cache) {
        if (currentDepth > maxDepth || realmObject == null) {
            return null;
        }
        CacheData<RealmModel> cachedObject = cache.get(realmObject);
        com.shiz.flighttime.database.FlightDB unmanagedObject;
        if (cachedObject != null) {
            // Reuse cached object or recreate it because it was encountered at a lower depth.
            if (currentDepth >= cachedObject.minDepth) {
                return (com.shiz.flighttime.database.FlightDB)cachedObject.object;
            } else {
                unmanagedObject = (com.shiz.flighttime.database.FlightDB)cachedObject.object;
                cachedObject.minDepth = currentDepth;
            }
        } else {
            unmanagedObject = new com.shiz.flighttime.database.FlightDB();
            cache.put(realmObject, new RealmObjectProxy.CacheData(currentDepth, unmanagedObject));
        }
        ((FlightDBRealmProxyInterface) unmanagedObject).realmSet$id(((FlightDBRealmProxyInterface) realmObject).realmGet$id());
        ((FlightDBRealmProxyInterface) unmanagedObject).realmSet$date(((FlightDBRealmProxyInterface) realmObject).realmGet$date());
        ((FlightDBRealmProxyInterface) unmanagedObject).realmSet$duration(((FlightDBRealmProxyInterface) realmObject).realmGet$duration());
        ((FlightDBRealmProxyInterface) unmanagedObject).realmSet$day(((FlightDBRealmProxyInterface) realmObject).realmGet$day());
        return unmanagedObject;
    }

    static com.shiz.flighttime.database.FlightDB update(Realm realm, com.shiz.flighttime.database.FlightDB realmObject, com.shiz.flighttime.database.FlightDB newObject, Map<RealmModel, RealmObjectProxy> cache) {
        ((FlightDBRealmProxyInterface) realmObject).realmSet$date(((FlightDBRealmProxyInterface) newObject).realmGet$date());
        ((FlightDBRealmProxyInterface) realmObject).realmSet$duration(((FlightDBRealmProxyInterface) newObject).realmGet$duration());
        ((FlightDBRealmProxyInterface) realmObject).realmSet$day(((FlightDBRealmProxyInterface) newObject).realmGet$day());
        return realmObject;
    }

    @Override
    public String toString() {
        if (!RealmObject.isValid(this)) {
            return "Invalid object";
        }
        StringBuilder stringBuilder = new StringBuilder("FlightDB = [");
        stringBuilder.append("{id:");
        stringBuilder.append(realmGet$id());
        stringBuilder.append("}");
        stringBuilder.append(",");
        stringBuilder.append("{date:");
        stringBuilder.append(realmGet$date());
        stringBuilder.append("}");
        stringBuilder.append(",");
        stringBuilder.append("{duration:");
        stringBuilder.append(realmGet$duration());
        stringBuilder.append("}");
        stringBuilder.append(",");
        stringBuilder.append("{day:");
        stringBuilder.append(realmGet$day());
        stringBuilder.append("}");
        stringBuilder.append("]");
        return stringBuilder.toString();
    }

    @Override
    public ProxyState realmGet$proxyState() {
        return proxyState;
    }

    @Override
    public int hashCode() {
        String realmName = proxyState.getRealm$realm().getPath();
        String tableName = proxyState.getRow$realm().getTable().getName();
        long rowIndex = proxyState.getRow$realm().getIndex();

        int result = 17;
        result = 31 * result + ((realmName != null) ? realmName.hashCode() : 0);
        result = 31 * result + ((tableName != null) ? tableName.hashCode() : 0);
        result = 31 * result + (int) (rowIndex ^ (rowIndex >>> 32));
        return result;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        FlightDBRealmProxy aFlightDB = (FlightDBRealmProxy)o;

        String path = proxyState.getRealm$realm().getPath();
        String otherPath = aFlightDB.proxyState.getRealm$realm().getPath();
        if (path != null ? !path.equals(otherPath) : otherPath != null) return false;;

        String tableName = proxyState.getRow$realm().getTable().getName();
        String otherTableName = aFlightDB.proxyState.getRow$realm().getTable().getName();
        if (tableName != null ? !tableName.equals(otherTableName) : otherTableName != null) return false;

        if (proxyState.getRow$realm().getIndex() != aFlightDB.proxyState.getRow$realm().getIndex()) return false;

        return true;
    }

}
