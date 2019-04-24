(ns clojure-kubernetes-client.specs.v1beta1-daemon-set-condition
  (:require [clojure.spec.alpha :as s]
            [spec-tools.data-spec :as ds]
            )
  (:import (java.io File)))


(declare v1beta1-daemon-set-condition-data v1beta1-daemon-set-condition)
(def v1beta1-daemon-set-condition-data
  {
   (ds/opt :lastTransitionTime) inst?
   (ds/opt :message) string?
   (ds/opt :reason) string?
   (ds/req :status) string?
   (ds/req :type) string?
   })

(def v1beta1-daemon-set-condition
  (ds/spec
    {:name ::v1beta1-daemon-set-condition
     :spec v1beta1-daemon-set-condition-data}))

