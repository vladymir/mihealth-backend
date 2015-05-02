(ns mihealth.handler
  (:require [mihealth.db :as midb]
            [mihealth.core :as micore])
  (:require [compojure.route :as route]
            [compojure.core :refer [GET defroutes]]
            [ring.util.response :refer [resource-response response]]
            [ring.middleware.json :as middleware]
            [ring.middleware.defaults :refer [wrap-defaults api-defaults site-defaults]]))

(defroutes app-routes
  (GET "/user/all" [] (micore/all-users));(str (dissoc (first (midb/find-all-users)) :_id))
  (GET "/user/find/:id" [id] (micore/user-by-id id))
  (route/not-found "Not Found"))

;(def app
 ; (wrap-defaults app-routes site-defaults))
(def app
  (-> app-routes
      (middleware/wrap-json-body)
      (middleware/wrap-json-response)
      (wrap-defaults api-defaults)))