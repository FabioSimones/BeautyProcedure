const DB_NAME = "ms-beautique-query";

db = db.getSiblingDB(DB_NAME);

try { db.dropUser("ms-beautique-query"); } catch (e) {}
try { db.dropUser("ms-sync"); } catch (e) {}

db.createUser({
  user: "ms-sync",
  pwd: "ms-sync",
  roles: [{ role: "readWrite", db: DB_NAME }]
});

db.createUser({
  user: "ms-beautique-query",
  pwd: "ms-beautique-query",
  roles: [{ role: "read", db: DB_NAME }]
});
