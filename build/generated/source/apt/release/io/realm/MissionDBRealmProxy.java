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

public class MissionDBRealmProxy extends com.shiz.flighttime.database.MissionDB
    implements RealmObjectProxy, MissionDBRealmProxyInterface {

    static final class MissionDBColumnInfo extends ColumnInfo {

        public final long cityIndex;
        public final long idIndex;
        public final long dateIndex;
        public final long durationIndex;
        public final long dayDurationIndex;
        public final long nightDurationIndex;
        public final long flightDBRealmListIndex;

        MissionDBColumnInfo(String path, Table table) {
            final Map<String, Long> indicesMap = new HashMap<String, Long>(7);
            this.cityIndex = getValidColumnIndex(path, table, "MissionDB", "city");
            indicesMap.put("city", this.cityIndex);

            this.idIndex = getValidColumnIndex(path, table, "MissionDB", "id");
            indicesMap.put("id", this.idIndex);

            this.dateIndex = getValidColumnIndex(path, table, "MissionDB", "date");
            indicesMap.put("date", this.dateIndex);

            this.durationIndex = getValidColumnIndex(path, table, "MissionDB", "duration");
            indicesMap.put("duration", this.durationIndex);

            this.dayDurationIndex = getValidColumnIndex(path, table, "MissionDB", "dayDuration");
            indicesMap.put("dayDuration", this.dayDurationIndex);

            this.nightDurationIndex = getValidColumnIndex(path, table, "MissionDB", "nightDuration");
            indicesMap.put("nightDuration", this.nightDurationIndex);

            this.flightDBRealmListIndex = getValidColumnIndex(path, table, "MissionDB", "flightDBRealmList");
            indicesMap.put("flightDBRealmList", this.flightDBRealmListIndex);

            setIndicesMap(indicesMap);
        }
    }

    private final MissionDBColumnInfo columnInfo;
    private final ProxyState proxyState;
    private RealmList<com.shiz.flighttime.database.FlightDB> flightDBRealmListRealmList;
    private static final List<String> FIELD_NAMES;
    static {
        List<String> fieldNames = new ArrayList<String>();
        fieldNames.add("city");
        fieldNames.add("id");
        fieldNames.add("date");
        fieldNames.add("duration");
        fieldNames.add("dayDuration");
        fieldNames.add("nightDuration");
        fieldNames.add("flightDBRealmList");
        FIELD_NAMES = Collections.unmodifiableList(fieldNames);
    }

    MissionDBRealmProxy(ColumnInfo columnInfo) {
        this.columnInfo = (MissionDBColumnInfo) columnInfo;
        this.proxyState = new ProxyState(com.shiz.flighttime.database.MissionDB.class, this);
    }

    @SuppressWarnings("cast")
    public String realmGet$city() {
        proxyState.getRealm$realm().checkIfValid();
        return (java.lang.String) proxyState.getRow$realm().getString(columnInfo.cityIndex);
    }

    public void realmSet$city(String value) {
        proxyState.getRealm$realm().checkIfValid();
        if (value == null) {
            throw new IllegalArgumentException("Trying to set non-nullable field 'city' to null.");
        }
        proxyState.getRow$realm().setString(columnInfo.cityIndex, value);
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
    public long realmGet$dayDuration() {
        proxyState.getRealm$realm().checkIfValid();
        return (long) proxyState.getRow$realm().getLong(columnInfo.dayDurationIndex);
    }

    public void realmSet$dayDuration(long value) {
        proxyState.getRealm$realm().checkIfValid();
        proxyState.getRow$realm().setLong(columnInfo.dayDurationIndex, value);
    }

    @SuppressWarnings("cast")
    public long realmGet$nightDuration() {
        proxyState.getRealm$realm().checkIfValid();
        return (long) proxyState.getRow$realm().getLong(columnInfo.nightDurationIndex);
    }

    public void realmSet$nightDuration(long value) {
        proxyState.getRealm$realm().checkIfValid();
        proxyState.getRow$realm().setLong(columnInfo.nightDurationIndex, value);
    }

    public RealmList<com.shiz.flighttime.database.FlightDB> realmGet$flightDBRealmList() {
        proxyState.getRealm$realm().checkIfValid();
        // use the cached value if available
        if (flightDBRealmListRealmList != null) {
            return flightDBRealmListRealmList;
        } else {
            LinkView linkView = proxyState.getRow$realm().getLinkList(columnInfo.flightDBRealmListIndex);
            flightDBRealmListRealmList = new RealmList<com.shiz.flighttime.database.FlightDB>(com.shiz.flighttime.database.FlightDB.class, linkView, proxyState.getRealm$realm());
            return flightDBRealmListRealmList;
        }
    }

    public void realmSet$flightDBRealmList(RealmList<com.shiz.flighttime.database.FlightDB> value) {
        proxyState.getRealm$realm().checkIfValid();
        LinkView links = proxyState.getRow$realm().getLinkList(columnInfo.flightDBRealmListIndex);
        links.clear();
        if (value == null) {
            return;
        }
        for (RealmModel linkedObject : (RealmList<? extends RealmModel>) value) {
            if (!RealmObject.isValid(linkedObject)) {
                throw new IllegalArgumentException("Each element of 'value' must be a valid managed object.");
            }
            if (((RealmObjectProxy)linkedObject).realmGet$proxyState().getRealm$realm() != proxyState.getRealm$realm()) {
                throw new IllegalArgumentException("Each element of 'value' must belong to the same Realm.");
            }
            links.add(((RealmObjectProxy)linkedObject).realmGet$proxyState().getRow$realm().getIndex());
        }
    }

    public static Table initTable(ImplicitTransaction transaction) {
        if (!transaction.hasTable("class_MissionDB")) {
            Table table = transaction.getTable("class_MissionDB");
            table.addColumn(RealmFieldType.STRING, "city", Table.NOT_NULLABLE);
            table.addColumn(RealmFieldType.INTEGER, "id", Table.NOT_NULLABLE);
            table.addColumn(RealmFieldType.DATE, "date", Table.NOT_NULLABLE);
            table.addColumn(RealmFieldType.INTEGER, "duration", Table.NOT_NULLABLE);
            table.addColumn(RealmFieldType.INTEGER, "dayDuration", Table.NOT_NULLABLE);
            table.addColumn(RealmFieldType.INTEGER, "nightDuration", Table.NOT_NULLABLE);
            if (!transaction.hasTable("class_FlightDB")) {
                FlightDBRealmProxy.initTable(transaction);
            }
            table.addColumnLink(RealmFieldType.LIST, "flightDBRealmList", transaction.getTable("class_FlightDB"));
            table.addSearchIndex(table.getColumnIndex("id"));
            table.setPrimaryKey("id");
            return table;
        }
        return transaction.getTable("class_MissionDB");
    }

    public static MissionDBColumnInfo validateTable(ImplicitTransaction transaction) {
        if (transaction.hasTable("class_MissionDB")) {
            Table table = transaction.getTable("class_MissionDB");
            if (table.getColumnCount() != 7) {
                throw new RealmMigrationNeededException(transaction.getPath(), "Field count does not match - expected 7 but was " + table.getColumnCount());
            }
            Map<String, RealmFieldType> columnTypes = new HashMap<String, RealmFieldType>();
            for (long i = 0; i < 7; i++) {
                columnTypes.put(table.getColumnName(i), table.getColumnType(i));
            }

            final MissionDBColumnInfo columnInfo = new MissionDBColumnInfo(transaction.getPath(), table);

            if (!columnTypes.containsKey("city")) {
                throw new RealmMigrationNeededException(transaction.getPath(), "Missing field 'city' in existing Realm file. Either remove field or migrate using io.realm.internal.Table.addColumn().");
            }
            if (columnTypes.get("city") != RealmFieldType.STRING) {
                throw new RealmMigrationNeededException(transaction.getPath(), "Invalid type 'String' for field 'city' in existing Realm file.");
            }
            if (table.isColumnNullable(columnInfo.cityIndex)) {
                throw new RealmMigrationNeededException(transaction.getPath(), "Field 'city' does support null values in the existing Realm file. Remove @Required or @PrimaryKey from field 'city' or migrate using RealmObjectSchema.setNullable().");
            }
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
            if (!columnTypes.containsKey("dayDuration")) {
                throw new RealmMigrationNeededException(transaction.getPath(), "Missing field 'dayDuration' in existing Realm file. Either remove field or migrate using io.realm.internal.Table.addColumn().");
            }
            if (columnTypes.get("dayDuration") != RealmFieldType.INTEGER) {
                throw new RealmMigrationNeededException(transaction.getPath(), "Invalid type 'long' for field 'dayDuration' in existing Realm file.");
            }
            if (table.isColumnNullable(columnInfo.dayDurationIndex)) {
                throw new RealmMigrationNeededException(transaction.getPath(), "Field 'dayDuration' does support null values in the existing Realm file. Use corresponding boxed type for field 'dayDuration' or migrate using RealmObjectSchema.setNullable().");
            }
            if (!columnTypes.containsKey("nightDuration")) {
                throw new RealmMigrationNeededException(transaction.getPath(), "Missing field 'nightDuration' in existing Realm file. Either remove field or migrate using io.realm.internal.Table.addColumn().");
            }
            if (columnTypes.get("nightDuration") != RealmFieldType.INTEGER) {
                throw new RealmMigrationNeededException(transaction.getPath(), "Invalid type 'long' for field 'nightDuration' in existing Realm file.");
            }
            if (table.isColumnNullable(columnInfo.nightDurationIndex)) {
                throw new RealmMigrationNeededException(transaction.getPath(), "Field 'nightDuration' does support null values in the existing Realm file. Use corresponding boxed type for field 'nightDuration' or migrate using RealmObjectSchema.setNullable().");
            }
            if (!columnTypes.containsKey("flightDBRealmList")) {
                throw new RealmMigrationNeededException(transaction.getPath(), "Missing field 'flightDBRealmList'");
            }
            if (columnTypes.get("flightDBRealmList") != RealmFieldType.LIST) {
                throw new RealmMigrationNeededException(transaction.getPath(), "Invalid type 'FlightDB' for field 'flightDBRealmList'");
            }
            if (!transaction.hasTable("class_FlightDB")) {
                throw new RealmMigrationNeededException(transaction.getPath(), "Missing class 'class_FlightDB' for field 'flightDBRealmList'");
            }
            Table table_6 = transaction.getTable("class_FlightDB");
            if (!table.getLinkTarget(columnInfo.flightDBRealmListIndex).hasSameSchema(table_6)) {
                throw new RealmMigrationNeededException(transaction.getPath(), "Invalid RealmList type for field 'flightDBRealmList': '" + table.getLinkTarget(columnInfo.flightDBRealmListIndex).getName() + "' expected - was '" + table_6.getName() + "'");
            }
            return columnInfo;
        } else {
            throw new RealmMigrationNeededException(transaction.getPath(), "The 'MissionDB' class is missing from the schema for this Realm.");
        }
    }

    public static String getTableName() {
        return "class_MissionDB";
    }

    public static List<String> getFieldNames() {
        return FIELD_NAMES;
    }

    @SuppressWarnings("cast")
    public static com.shiz.flighttime.database.MissionDB createOrUpdateUsingJsonObject(Realm realm, JSONObject json, boolean update)
        throws JSONException {
        com.shiz.flighttime.database.MissionDB obj = null;
        if (update) {
            Table table = realm.getTable(com.shiz.flighttime.database.MissionDB.class);
            long pkColumnIndex = table.getPrimaryKey();
            long rowIndex = TableOrView.NO_MATCH;
            if (!json.isNull("id")) {
                rowIndex = table.findFirstLong(pkColumnIndex, json.getLong("id"));
            }
            if (rowIndex != TableOrView.NO_MATCH) {
                obj = new io.realm.MissionDBRealmProxy(realm.schema.getColumnInfo(com.shiz.flighttime.database.MissionDB.class));
                ((RealmObjectProxy)obj).realmGet$proxyState().setRealm$realm(realm);
                ((RealmObjectProxy)obj).realmGet$proxyState().setRow$realm(table.getUncheckedRow(rowIndex));
            }
        }
        if (obj == null) {
            if (json.has("id")) {
                if (json.isNull("id")) {
                    obj = (io.realm.MissionDBRealmProxy) realm.createObject(com.shiz.flighttime.database.MissionDB.class, null);
                } else {
                    obj = (io.realm.MissionDBRealmProxy) realm.createObject(com.shiz.flighttime.database.MissionDB.class, json.getInt("id"));
                }
            } else {
                obj = (io.realm.MissionDBRealmProxy) realm.createObject(com.shiz.flighttime.database.MissionDB.class);
            }
        }
        if (json.has("city")) {
            if (json.isNull("city")) {
                ((MissionDBRealmProxyInterface) obj).realmSet$city(null);
            } else {
                ((MissionDBRealmProxyInterface) obj).realmSet$city((String) json.getString("city"));
            }
        }
        if (json.has("id")) {
            if (json.isNull("id")) {
                throw new IllegalArgumentException("Trying to set non-nullable field 'id' to null.");
            } else {
                ((MissionDBRealmProxyInterface) obj).realmSet$id((int) json.getInt("id"));
            }
        }
        if (json.has("date")) {
            if (json.isNull("date")) {
                ((MissionDBRealmProxyInterface) obj).realmSet$date(null);
            } else {
                Object timestamp = json.get("date");
                if (timestamp instanceof String) {
                    ((MissionDBRealmProxyInterface) obj).realmSet$date(JsonUtils.stringToDate((String) timestamp));
                } else {
                    ((MissionDBRealmProxyInterface) obj).realmSet$date(new Date(json.getLong("date")));
                }
            }
        }
        if (json.has("duration")) {
            if (json.isNull("duration")) {
                throw new IllegalArgumentException("Trying to set non-nullable field 'duration' to null.");
            } else {
                ((MissionDBRealmProxyInterface) obj).realmSet$duration((long) json.getLong("duration"));
            }
        }
        if (json.has("dayDuration")) {
            if (json.isNull("dayDuration")) {
                throw new IllegalArgumentException("Trying to set non-nullable field 'dayDuration' to null.");
            } else {
                ((MissionDBRealmProxyInterface) obj).realmSet$dayDuration((long) json.getLong("dayDuration"));
            }
        }
        if (json.has("nightDuration")) {
            if (json.isNull("nightDuration")) {
                throw new IllegalArgumentException("Trying to set non-nullable field 'nightDuration' to null.");
            } else {
                ((MissionDBRealmProxyInterface) obj).realmSet$nightDuration((long) json.getLong("nightDuration"));
            }
        }
        if (json.has("flightDBRealmList")) {
            if (json.isNull("flightDBRealmList")) {
                ((MissionDBRealmProxyInterface) obj).realmSet$flightDBRealmList(null);
            } else {
                ((MissionDBRealmProxyInterface) obj).realmGet$flightDBRealmList().clear();
                JSONArray array = json.getJSONArray("flightDBRealmList");
                for (int i = 0; i < array.length(); i++) {
                    com.shiz.flighttime.database.FlightDB item = FlightDBRealmProxy.createOrUpdateUsingJsonObject(realm, array.getJSONObject(i), update);
                    ((MissionDBRealmProxyInterface) obj).realmGet$flightDBRealmList().add(item);
                }
            }
        }
        return obj;
    }

    @SuppressWarnings("cast")
    public static com.shiz.flighttime.database.MissionDB createUsingJsonStream(Realm realm, JsonReader reader)
        throws IOException {
        com.shiz.flighttime.database.MissionDB obj = realm.createObject(com.shiz.flighttime.database.MissionDB.class);
        reader.beginObject();
        while (reader.hasNext()) {
            String name = reader.nextName();
            if (name.equals("city")) {
                if (reader.peek() == JsonToken.NULL) {
                    reader.skipValue();
                    ((MissionDBRealmProxyInterface) obj).realmSet$city(null);
                } else {
                    ((MissionDBRealmProxyInterface) obj).realmSet$city((String) reader.nextString());
                }
            } else if (name.equals("id")) {
                if (reader.peek() == JsonToken.NULL) {
                    reader.skipValue();
                    throw new IllegalArgumentException("Trying to set non-nullable field 'id' to null.");
                } else {
                    ((MissionDBRealmProxyInterface) obj).realmSet$id((int) reader.nextInt());
                }
            } else if (name.equals("date")) {
                if (reader.peek() == JsonToken.NULL) {
                    reader.skipValue();
                    ((MissionDBRealmProxyInterface) obj).realmSet$date(null);
                } else if (reader.peek() == JsonToken.NUMBER) {
                    long timestamp = reader.nextLong();
                    if (timestamp > -1) {
                        ((MissionDBRealmProxyInterface) obj).realmSet$date(new Date(timestamp));
                    }
                } else {
                    ((MissionDBRealmProxyInterface) obj).realmSet$date(JsonUtils.stringToDate(reader.nextString()));
                }
            } else if (name.equals("duration")) {
                if (reader.peek() == JsonToken.NULL) {
                    reader.skipValue();
                    throw new IllegalArgumentException("Trying to set non-nullable field 'duration' to null.");
                } else {
                    ((MissionDBRealmProxyInterface) obj).realmSet$duration((long) reader.nextLong());
                }
            } else if (name.equals("dayDuration")) {
                if (reader.peek() == JsonToken.NULL) {
                    reader.skipValue();
                    throw new IllegalArgumentException("Trying to set non-nullable field 'dayDuration' to null.");
                } else {
                    ((MissionDBRealmProxyInterface) obj).realmSet$dayDuration((long) reader.nextLong());
                }
            } else if (name.equals("nightDuration")) {
                if (reader.peek() == JsonToken.NULL) {
                    reader.skipValue();
                    throw new IllegalArgumentException("Trying to set non-nullable field 'nightDuration' to null.");
                } else {
                    ((MissionDBRealmProxyInterface) obj).realmSet$nightDuration((long) reader.nextLong());
                }
            } else if (name.equals("flightDBRealmList")) {
                if (reader.peek() == JsonToken.NULL) {
                    reader.skipValue();
                    ((MissionDBRealmProxyInterface) obj).realmSet$flightDBRealmList(null);
                } else {
                    reader.beginArray();
                    while (reader.hasNext()) {
                        com.shiz.flighttime.database.FlightDB item = FlightDBRealmProxy.createUsingJsonStream(realm, reader);
                        ((MissionDBRealmProxyInterface) obj).realmGet$flightDBRealmList().add(item);
                    }
                    reader.endArray();
                }
            } else {
                reader.skipValue();
            }
        }
        reader.endObject();
        return obj;
    }

    public static com.shiz.flighttime.database.MissionDB copyOrUpdate(Realm realm, com.shiz.flighttime.database.MissionDB object, boolean update, Map<RealmModel,RealmObjectProxy> cache) {
        if (object instanceof RealmObjectProxy && ((RealmObjectProxy) object).realmGet$proxyState().getRealm$realm() != null && ((RealmObjectProxy) object).realmGet$proxyState().getRealm$realm().threadId != realm.threadId) {
            throw new IllegalArgumentException("Objects which belong to Realm instances in other threads cannot be copied into this Realm instance.");
        }
        if (object instanceof RealmObjectProxy && ((RealmObjectProxy)object).realmGet$proxyState().getRealm$realm() != null && ((RealmObjectProxy)object).realmGet$proxyState().getRealm$realm().getPath().equals(realm.getPath())) {
            return object;
        }
        RealmObjectProxy cachedRealmObject = cache.get(object);
        if (cachedRealmObject != null) {
            return (com.shiz.flighttime.database.MissionDB) cachedRealmObject;
        } else {
            com.shiz.flighttime.database.MissionDB realmObject = null;
            boolean canUpdate = update;
            if (canUpdate) {
                Table table = realm.getTable(com.shiz.flighttime.database.MissionDB.class);
                long pkColumnIndex = table.getPrimaryKey();
                long rowIndex = table.findFirstLong(pkColumnIndex, ((MissionDBRealmProxyInterface) object).realmGet$id());
                if (rowIndex != TableOrView.NO_MATCH) {
                    realmObject = new io.realm.MissionDBRealmProxy(realm.schema.getColumnInfo(com.shiz.flighttime.database.MissionDB.class));
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

    public static com.shiz.flighttime.database.MissionDB copy(Realm realm, com.shiz.flighttime.database.MissionDB newObject, boolean update, Map<RealmModel,RealmObjectProxy> cache) {
        RealmObjectProxy cachedRealmObject = cache.get(newObject);
        if (cachedRealmObject != null) {
            return (com.shiz.flighttime.database.MissionDB) cachedRealmObject;
        } else {
            com.shiz.flighttime.database.MissionDB realmObject = realm.createObject(com.shiz.flighttime.database.MissionDB.class, ((MissionDBRealmProxyInterface) newObject).realmGet$id());
            cache.put(newObject, (RealmObjectProxy) realmObject);
            ((MissionDBRealmProxyInterface) realmObject).realmSet$city(((MissionDBRealmProxyInterface) newObject).realmGet$city());
            ((MissionDBRealmProxyInterface) realmObject).realmSet$id(((MissionDBRealmProxyInterface) newObject).realmGet$id());
            ((MissionDBRealmProxyInterface) realmObject).realmSet$date(((MissionDBRealmProxyInterface) newObject).realmGet$date());
            ((MissionDBRealmProxyInterface) realmObject).realmSet$duration(((MissionDBRealmProxyInterface) newObject).realmGet$duration());
            ((MissionDBRealmProxyInterface) realmObject).realmSet$dayDuration(((MissionDBRealmProxyInterface) newObject).realmGet$dayDuration());
            ((MissionDBRealmProxyInterface) realmObject).realmSet$nightDuration(((MissionDBRealmProxyInterface) newObject).realmGet$nightDuration());

            RealmList<com.shiz.flighttime.database.FlightDB> flightDBRealmListList = ((MissionDBRealmProxyInterface) newObject).realmGet$flightDBRealmList();
            if (flightDBRealmListList != null) {
                RealmList<com.shiz.flighttime.database.FlightDB> flightDBRealmListRealmList = ((MissionDBRealmProxyInterface) realmObject).realmGet$flightDBRealmList();
                for (int i = 0; i < flightDBRealmListList.size(); i++) {
                    com.shiz.flighttime.database.FlightDB flightDBRealmListItem = flightDBRealmListList.get(i);
                    com.shiz.flighttime.database.FlightDB cacheflightDBRealmList = (com.shiz.flighttime.database.FlightDB) cache.get(flightDBRealmListItem);
                    if (cacheflightDBRealmList != null) {
                        flightDBRealmListRealmList.add(cacheflightDBRealmList);
                    } else {
                        flightDBRealmListRealmList.add(FlightDBRealmProxy.copyOrUpdate(realm, flightDBRealmListList.get(i), update, cache));
                    }
                }
            }

            return realmObject;
        }
    }

    public static long insert(Realm realm, com.shiz.flighttime.database.MissionDB object, Map<RealmModel,Long> cache) {
        if (object instanceof RealmObjectProxy && ((RealmObjectProxy)object).realmGet$proxyState().getRealm$realm() != null && ((RealmObjectProxy)object).realmGet$proxyState().getRealm$realm().getPath().equals(realm.getPath())) {
            return ((RealmObjectProxy)object).realmGet$proxyState().getRow$realm().getIndex();
        }
        Table table = realm.getTable(com.shiz.flighttime.database.MissionDB.class);
        long tableNativePtr = table.getNativeTablePointer();
        MissionDBColumnInfo columnInfo = (MissionDBColumnInfo) realm.schema.getColumnInfo(com.shiz.flighttime.database.MissionDB.class);
        long pkColumnIndex = table.getPrimaryKey();
        long rowIndex = TableOrView.NO_MATCH;
        Object primaryKeyValue = ((MissionDBRealmProxyInterface) object).realmGet$id();
        if (primaryKeyValue != null) {
            rowIndex = Table.nativeFindFirstInt(tableNativePtr, pkColumnIndex, ((MissionDBRealmProxyInterface) object).realmGet$id());
        }
        if (rowIndex == TableOrView.NO_MATCH) {
            rowIndex = Table.nativeAddEmptyRow(tableNativePtr, 1);
            if (primaryKeyValue != null) {
                Table.nativeSetLong(tableNativePtr, pkColumnIndex, rowIndex, ((MissionDBRealmProxyInterface) object).realmGet$id());
            }
        } else {
            Table.throwDuplicatePrimaryKeyException(primaryKeyValue);
        }
        cache.put(object, rowIndex);
        String realmGet$city = ((MissionDBRealmProxyInterface)object).realmGet$city();
        if (realmGet$city != null) {
            Table.nativeSetString(tableNativePtr, columnInfo.cityIndex, rowIndex, realmGet$city);
        }
        java.util.Date realmGet$date = ((MissionDBRealmProxyInterface)object).realmGet$date();
        if (realmGet$date != null) {
            Table.nativeSetTimestamp(tableNativePtr, columnInfo.dateIndex, rowIndex, realmGet$date.getTime());
        }
        Table.nativeSetLong(tableNativePtr, columnInfo.durationIndex, rowIndex, ((MissionDBRealmProxyInterface)object).realmGet$duration());
        Table.nativeSetLong(tableNativePtr, columnInfo.dayDurationIndex, rowIndex, ((MissionDBRealmProxyInterface)object).realmGet$dayDuration());
        Table.nativeSetLong(tableNativePtr, columnInfo.nightDurationIndex, rowIndex, ((MissionDBRealmProxyInterface)object).realmGet$nightDuration());

        RealmList<com.shiz.flighttime.database.FlightDB> flightDBRealmListList = ((MissionDBRealmProxyInterface) object).realmGet$flightDBRealmList();
        if (flightDBRealmListList != null) {
            long flightDBRealmListNativeLinkViewPtr = Table.nativeGetLinkView(tableNativePtr, columnInfo.flightDBRealmListIndex, rowIndex);
            for (com.shiz.flighttime.database.FlightDB flightDBRealmListItem : flightDBRealmListList) {
                Long cacheItemIndexflightDBRealmList = cache.get(flightDBRealmListItem);
                if (cacheItemIndexflightDBRealmList == null) {
                    cacheItemIndexflightDBRealmList = FlightDBRealmProxy.insert(realm, flightDBRealmListItem, cache);
                }
                LinkView.nativeAdd(flightDBRealmListNativeLinkViewPtr, cacheItemIndexflightDBRealmList);
            }
            LinkView.nativeClose(flightDBRealmListNativeLinkViewPtr);
        }

        return rowIndex;
    }

    public static void insert(Realm realm, Iterator<? extends RealmModel> objects, Map<RealmModel,Long> cache) {
        Table table = realm.getTable(com.shiz.flighttime.database.MissionDB.class);
        long tableNativePtr = table.getNativeTablePointer();
        MissionDBColumnInfo columnInfo = (MissionDBColumnInfo) realm.schema.getColumnInfo(com.shiz.flighttime.database.MissionDB.class);
        long pkColumnIndex = table.getPrimaryKey();
        com.shiz.flighttime.database.MissionDB object = null;
        while (objects.hasNext()) {
            object = (com.shiz.flighttime.database.MissionDB) objects.next();
            if(!cache.containsKey(object)) {
                if (object instanceof RealmObjectProxy && ((RealmObjectProxy)object).realmGet$proxyState().getRealm$realm() != null && ((RealmObjectProxy)object).realmGet$proxyState().getRealm$realm().getPath().equals(realm.getPath())) {
                    cache.put(object, ((RealmObjectProxy)object).realmGet$proxyState().getRow$realm().getIndex());
                    continue;
                }
                long rowIndex = TableOrView.NO_MATCH;
                Object primaryKeyValue = ((MissionDBRealmProxyInterface) object).realmGet$id();
                if (primaryKeyValue != null) {
                    rowIndex = Table.nativeFindFirstInt(tableNativePtr, pkColumnIndex, ((MissionDBRealmProxyInterface) object).realmGet$id());
                }
                if (rowIndex == TableOrView.NO_MATCH) {
                    rowIndex = Table.nativeAddEmptyRow(tableNativePtr, 1);
                    if (primaryKeyValue != null) {
                        Table.nativeSetLong(tableNativePtr, pkColumnIndex, rowIndex, ((MissionDBRealmProxyInterface) object).realmGet$id());
                    }
                } else {
                    Table.throwDuplicatePrimaryKeyException(primaryKeyValue);
                }
                cache.put(object, rowIndex);
                String realmGet$city = ((MissionDBRealmProxyInterface)object).realmGet$city();
                if (realmGet$city != null) {
                    Table.nativeSetString(tableNativePtr, columnInfo.cityIndex, rowIndex, realmGet$city);
                }
                java.util.Date realmGet$date = ((MissionDBRealmProxyInterface)object).realmGet$date();
                if (realmGet$date != null) {
                    Table.nativeSetTimestamp(tableNativePtr, columnInfo.dateIndex, rowIndex, realmGet$date.getTime());
                }
                Table.nativeSetLong(tableNativePtr, columnInfo.durationIndex, rowIndex, ((MissionDBRealmProxyInterface)object).realmGet$duration());
                Table.nativeSetLong(tableNativePtr, columnInfo.dayDurationIndex, rowIndex, ((MissionDBRealmProxyInterface)object).realmGet$dayDuration());
                Table.nativeSetLong(tableNativePtr, columnInfo.nightDurationIndex, rowIndex, ((MissionDBRealmProxyInterface)object).realmGet$nightDuration());

                RealmList<com.shiz.flighttime.database.FlightDB> flightDBRealmListList = ((MissionDBRealmProxyInterface) object).realmGet$flightDBRealmList();
                if (flightDBRealmListList != null) {
                    long flightDBRealmListNativeLinkViewPtr = Table.nativeGetLinkView(tableNativePtr, columnInfo.flightDBRealmListIndex, rowIndex);
                    for (com.shiz.flighttime.database.FlightDB flightDBRealmListItem : flightDBRealmListList) {
                        Long cacheItemIndexflightDBRealmList = cache.get(flightDBRealmListItem);
                        if (cacheItemIndexflightDBRealmList == null) {
                            cacheItemIndexflightDBRealmList = FlightDBRealmProxy.insert(realm, flightDBRealmListItem, cache);
                        }
                        LinkView.nativeAdd(flightDBRealmListNativeLinkViewPtr, cacheItemIndexflightDBRealmList);
                    }
                    LinkView.nativeClose(flightDBRealmListNativeLinkViewPtr);
                }

            }
        }
    }

    public static long insertOrUpdate(Realm realm, com.shiz.flighttime.database.MissionDB object, Map<RealmModel,Long> cache) {
        if (object instanceof RealmObjectProxy && ((RealmObjectProxy)object).realmGet$proxyState().getRealm$realm() != null && ((RealmObjectProxy)object).realmGet$proxyState().getRealm$realm().getPath().equals(realm.getPath())) {
            return ((RealmObjectProxy)object).realmGet$proxyState().getRow$realm().getIndex();
        }
        Table table = realm.getTable(com.shiz.flighttime.database.MissionDB.class);
        long tableNativePtr = table.getNativeTablePointer();
        MissionDBColumnInfo columnInfo = (MissionDBColumnInfo) realm.schema.getColumnInfo(com.shiz.flighttime.database.MissionDB.class);
        long pkColumnIndex = table.getPrimaryKey();
        long rowIndex = TableOrView.NO_MATCH;
        Object primaryKeyValue = ((MissionDBRealmProxyInterface) object).realmGet$id();
        if (primaryKeyValue != null) {
            rowIndex = Table.nativeFindFirstInt(tableNativePtr, pkColumnIndex, ((MissionDBRealmProxyInterface) object).realmGet$id());
        }
        if (rowIndex == TableOrView.NO_MATCH) {
            rowIndex = Table.nativeAddEmptyRow(tableNativePtr, 1);
            if (primaryKeyValue != null) {
                Table.nativeSetLong(tableNativePtr, pkColumnIndex, rowIndex, ((MissionDBRealmProxyInterface) object).realmGet$id());
            }
        }
        cache.put(object, rowIndex);
        String realmGet$city = ((MissionDBRealmProxyInterface)object).realmGet$city();
        if (realmGet$city != null) {
            Table.nativeSetString(tableNativePtr, columnInfo.cityIndex, rowIndex, realmGet$city);
        } else {
            Table.nativeSetNull(tableNativePtr, columnInfo.cityIndex, rowIndex);
        }
        java.util.Date realmGet$date = ((MissionDBRealmProxyInterface)object).realmGet$date();
        if (realmGet$date != null) {
            Table.nativeSetTimestamp(tableNativePtr, columnInfo.dateIndex, rowIndex, realmGet$date.getTime());
        } else {
            Table.nativeSetNull(tableNativePtr, columnInfo.dateIndex, rowIndex);
        }
        Table.nativeSetLong(tableNativePtr, columnInfo.durationIndex, rowIndex, ((MissionDBRealmProxyInterface)object).realmGet$duration());
        Table.nativeSetLong(tableNativePtr, columnInfo.dayDurationIndex, rowIndex, ((MissionDBRealmProxyInterface)object).realmGet$dayDuration());
        Table.nativeSetLong(tableNativePtr, columnInfo.nightDurationIndex, rowIndex, ((MissionDBRealmProxyInterface)object).realmGet$nightDuration());

        long flightDBRealmListNativeLinkViewPtr = Table.nativeGetLinkView(tableNativePtr, columnInfo.flightDBRealmListIndex, rowIndex);
        LinkView.nativeClear(flightDBRealmListNativeLinkViewPtr);
        RealmList<com.shiz.flighttime.database.FlightDB> flightDBRealmListList = ((MissionDBRealmProxyInterface) object).realmGet$flightDBRealmList();
        if (flightDBRealmListList != null) {
            for (com.shiz.flighttime.database.FlightDB flightDBRealmListItem : flightDBRealmListList) {
                Long cacheItemIndexflightDBRealmList = cache.get(flightDBRealmListItem);
                if (cacheItemIndexflightDBRealmList == null) {
                    cacheItemIndexflightDBRealmList = FlightDBRealmProxy.insertOrUpdate(realm, flightDBRealmListItem, cache);
                }
                LinkView.nativeAdd(flightDBRealmListNativeLinkViewPtr, cacheItemIndexflightDBRealmList);
            }
        }
        LinkView.nativeClose(flightDBRealmListNativeLinkViewPtr);

        return rowIndex;
    }

    public static void insertOrUpdate(Realm realm, Iterator<? extends RealmModel> objects, Map<RealmModel,Long> cache) {
        Table table = realm.getTable(com.shiz.flighttime.database.MissionDB.class);
        long tableNativePtr = table.getNativeTablePointer();
        MissionDBColumnInfo columnInfo = (MissionDBColumnInfo) realm.schema.getColumnInfo(com.shiz.flighttime.database.MissionDB.class);
        long pkColumnIndex = table.getPrimaryKey();
        com.shiz.flighttime.database.MissionDB object = null;
        while (objects.hasNext()) {
            object = (com.shiz.flighttime.database.MissionDB) objects.next();
            if(!cache.containsKey(object)) {
                if (object instanceof RealmObjectProxy && ((RealmObjectProxy)object).realmGet$proxyState().getRealm$realm() != null && ((RealmObjectProxy)object).realmGet$proxyState().getRealm$realm().getPath().equals(realm.getPath())) {
                    cache.put(object, ((RealmObjectProxy)object).realmGet$proxyState().getRow$realm().getIndex());
                    continue;
                }
                long rowIndex = TableOrView.NO_MATCH;
                Object primaryKeyValue = ((MissionDBRealmProxyInterface) object).realmGet$id();
                if (primaryKeyValue != null) {
                    rowIndex = Table.nativeFindFirstInt(tableNativePtr, pkColumnIndex, ((MissionDBRealmProxyInterface) object).realmGet$id());
                }
                if (rowIndex == TableOrView.NO_MATCH) {
                    rowIndex = Table.nativeAddEmptyRow(tableNativePtr, 1);
                    if (primaryKeyValue != null) {
                        Table.nativeSetLong(tableNativePtr, pkColumnIndex, rowIndex, ((MissionDBRealmProxyInterface) object).realmGet$id());
                    }
                }
                cache.put(object, rowIndex);
                String realmGet$city = ((MissionDBRealmProxyInterface)object).realmGet$city();
                if (realmGet$city != null) {
                    Table.nativeSetString(tableNativePtr, columnInfo.cityIndex, rowIndex, realmGet$city);
                } else {
                    Table.nativeSetNull(tableNativePtr, columnInfo.cityIndex, rowIndex);
                }
                java.util.Date realmGet$date = ((MissionDBRealmProxyInterface)object).realmGet$date();
                if (realmGet$date != null) {
                    Table.nativeSetTimestamp(tableNativePtr, columnInfo.dateIndex, rowIndex, realmGet$date.getTime());
                } else {
                    Table.nativeSetNull(tableNativePtr, columnInfo.dateIndex, rowIndex);
                }
                Table.nativeSetLong(tableNativePtr, columnInfo.durationIndex, rowIndex, ((MissionDBRealmProxyInterface)object).realmGet$duration());
                Table.nativeSetLong(tableNativePtr, columnInfo.dayDurationIndex, rowIndex, ((MissionDBRealmProxyInterface)object).realmGet$dayDuration());
                Table.nativeSetLong(tableNativePtr, columnInfo.nightDurationIndex, rowIndex, ((MissionDBRealmProxyInterface)object).realmGet$nightDuration());

                long flightDBRealmListNativeLinkViewPtr = Table.nativeGetLinkView(tableNativePtr, columnInfo.flightDBRealmListIndex, rowIndex);
                LinkView.nativeClear(flightDBRealmListNativeLinkViewPtr);
                RealmList<com.shiz.flighttime.database.FlightDB> flightDBRealmListList = ((MissionDBRealmProxyInterface) object).realmGet$flightDBRealmList();
                if (flightDBRealmListList != null) {
                    for (com.shiz.flighttime.database.FlightDB flightDBRealmListItem : flightDBRealmListList) {
                        Long cacheItemIndexflightDBRealmList = cache.get(flightDBRealmListItem);
                        if (cacheItemIndexflightDBRealmList == null) {
                            cacheItemIndexflightDBRealmList = FlightDBRealmProxy.insertOrUpdate(realm, flightDBRealmListItem, cache);
                        }
                        LinkView.nativeAdd(flightDBRealmListNativeLinkViewPtr, cacheItemIndexflightDBRealmList);
                    }
                }
                LinkView.nativeClose(flightDBRealmListNativeLinkViewPtr);

            }
        }
    }

    public static com.shiz.flighttime.database.MissionDB createDetachedCopy(com.shiz.flighttime.database.MissionDB realmObject, int currentDepth, int maxDepth, Map<RealmModel, CacheData<RealmModel>> cache) {
        if (currentDepth > maxDepth || realmObject == null) {
            return null;
        }
        CacheData<RealmModel> cachedObject = cache.get(realmObject);
        com.shiz.flighttime.database.MissionDB unmanagedObject;
        if (cachedObject != null) {
            // Reuse cached object or recreate it because it was encountered at a lower depth.
            if (currentDepth >= cachedObject.minDepth) {
                return (com.shiz.flighttime.database.MissionDB)cachedObject.object;
            } else {
                unmanagedObject = (com.shiz.flighttime.database.MissionDB)cachedObject.object;
                cachedObject.minDepth = currentDepth;
            }
        } else {
            unmanagedObject = new com.shiz.flighttime.database.MissionDB();
            cache.put(realmObject, new RealmObjectProxy.CacheData(currentDepth, unmanagedObject));
        }
        ((MissionDBRealmProxyInterface) unmanagedObject).realmSet$city(((MissionDBRealmProxyInterface) realmObject).realmGet$city());
        ((MissionDBRealmProxyInterface) unmanagedObject).realmSet$id(((MissionDBRealmProxyInterface) realmObject).realmGet$id());
        ((MissionDBRealmProxyInterface) unmanagedObject).realmSet$date(((MissionDBRealmProxyInterface) realmObject).realmGet$date());
        ((MissionDBRealmProxyInterface) unmanagedObject).realmSet$duration(((MissionDBRealmProxyInterface) realmObject).realmGet$duration());
        ((MissionDBRealmProxyInterface) unmanagedObject).realmSet$dayDuration(((MissionDBRealmProxyInterface) realmObject).realmGet$dayDuration());
        ((MissionDBRealmProxyInterface) unmanagedObject).realmSet$nightDuration(((MissionDBRealmProxyInterface) realmObject).realmGet$nightDuration());

        // Deep copy of flightDBRealmList
        if (currentDepth == maxDepth) {
            ((MissionDBRealmProxyInterface) unmanagedObject).realmSet$flightDBRealmList(null);
        } else {
            RealmList<com.shiz.flighttime.database.FlightDB> managedflightDBRealmListList = ((MissionDBRealmProxyInterface) realmObject).realmGet$flightDBRealmList();
            RealmList<com.shiz.flighttime.database.FlightDB> unmanagedflightDBRealmListList = new RealmList<com.shiz.flighttime.database.FlightDB>();
            ((MissionDBRealmProxyInterface) unmanagedObject).realmSet$flightDBRealmList(unmanagedflightDBRealmListList);
            int nextDepth = currentDepth + 1;
            int size = managedflightDBRealmListList.size();
            for (int i = 0; i < size; i++) {
                com.shiz.flighttime.database.FlightDB item = FlightDBRealmProxy.createDetachedCopy(managedflightDBRealmListList.get(i), nextDepth, maxDepth, cache);
                unmanagedflightDBRealmListList.add(item);
            }
        }
        return unmanagedObject;
    }

    static com.shiz.flighttime.database.MissionDB update(Realm realm, com.shiz.flighttime.database.MissionDB realmObject, com.shiz.flighttime.database.MissionDB newObject, Map<RealmModel, RealmObjectProxy> cache) {
        ((MissionDBRealmProxyInterface) realmObject).realmSet$city(((MissionDBRealmProxyInterface) newObject).realmGet$city());
        ((MissionDBRealmProxyInterface) realmObject).realmSet$date(((MissionDBRealmProxyInterface) newObject).realmGet$date());
        ((MissionDBRealmProxyInterface) realmObject).realmSet$duration(((MissionDBRealmProxyInterface) newObject).realmGet$duration());
        ((MissionDBRealmProxyInterface) realmObject).realmSet$dayDuration(((MissionDBRealmProxyInterface) newObject).realmGet$dayDuration());
        ((MissionDBRealmProxyInterface) realmObject).realmSet$nightDuration(((MissionDBRealmProxyInterface) newObject).realmGet$nightDuration());
        RealmList<com.shiz.flighttime.database.FlightDB> flightDBRealmListList = ((MissionDBRealmProxyInterface) newObject).realmGet$flightDBRealmList();
        RealmList<com.shiz.flighttime.database.FlightDB> flightDBRealmListRealmList = ((MissionDBRealmProxyInterface) realmObject).realmGet$flightDBRealmList();
        flightDBRealmListRealmList.clear();
        if (flightDBRealmListList != null) {
            for (int i = 0; i < flightDBRealmListList.size(); i++) {
                com.shiz.flighttime.database.FlightDB flightDBRealmListItem = flightDBRealmListList.get(i);
                com.shiz.flighttime.database.FlightDB cacheflightDBRealmList = (com.shiz.flighttime.database.FlightDB) cache.get(flightDBRealmListItem);
                if (cacheflightDBRealmList != null) {
                    flightDBRealmListRealmList.add(cacheflightDBRealmList);
                } else {
                    flightDBRealmListRealmList.add(FlightDBRealmProxy.copyOrUpdate(realm, flightDBRealmListList.get(i), true, cache));
                }
            }
        }
        return realmObject;
    }

    @Override
    public String toString() {
        if (!RealmObject.isValid(this)) {
            return "Invalid object";
        }
        StringBuilder stringBuilder = new StringBuilder("MissionDB = [");
        stringBuilder.append("{city:");
        stringBuilder.append(realmGet$city());
        stringBuilder.append("}");
        stringBuilder.append(",");
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
        stringBuilder.append("{dayDuration:");
        stringBuilder.append(realmGet$dayDuration());
        stringBuilder.append("}");
        stringBuilder.append(",");
        stringBuilder.append("{nightDuration:");
        stringBuilder.append(realmGet$nightDuration());
        stringBuilder.append("}");
        stringBuilder.append(",");
        stringBuilder.append("{flightDBRealmList:");
        stringBuilder.append("RealmList<FlightDB>[").append(realmGet$flightDBRealmList().size()).append("]");
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
        MissionDBRealmProxy aMissionDB = (MissionDBRealmProxy)o;

        String path = proxyState.getRealm$realm().getPath();
        String otherPath = aMissionDB.proxyState.getRealm$realm().getPath();
        if (path != null ? !path.equals(otherPath) : otherPath != null) return false;;

        String tableName = proxyState.getRow$realm().getTable().getName();
        String otherTableName = aMissionDB.proxyState.getRow$realm().getTable().getName();
        if (tableName != null ? !tableName.equals(otherTableName) : otherTableName != null) return false;

        if (proxyState.getRow$realm().getIndex() != aMissionDB.proxyState.getRow$realm().getIndex()) return false;

        return true;
    }

}
