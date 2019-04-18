(ns kubernetes.specs.v1beta1/resource-rule
  (:require [clojure.spec.alpha :as s]
            [spec-tools.data-spec :as ds]
            )
  (:import (java.io File)))


(def v1beta1/resource-rule-data
  {
   (ds/opt :apiGroups) (s/coll-of string?)
   (ds/opt :resourceNames) (s/coll-of string?)
   (ds/opt :resources) (s/coll-of string?)
   (ds/req :verbs) (s/coll-of string?)
   })

(def v1beta1/resource-rule-spec
  (ds/spec
    {:name ::v1beta1/resource-rule
     :spec v1beta1/resource-rule-data}))
