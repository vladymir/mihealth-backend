(ns mihealth.core
    (:require [mihealth.db :as midb]))

(defn all-users []
    ;(str (dissoc (first (midb/find-all-users)) :_id)))
    (str (first (midb/find-all-users))))

(defn user-by-id [id]
    id)