(ns kubernetes.specs.v1beta1/subject
  (:require [clojure.spec.alpha :as s]
            [spec-tools.data-spec :as ds]
            )
  (:import (java.io File)))


(def v1beta1/subject-data
  {
   (ds/opt :apiGroup) string?
   (ds/req :kind) string?
   (ds/req :name) string?
   (ds/opt :namespace) string?
   })

(def v1beta1/subject-spec
  (ds/spec
    {:name ::v1beta1/subject
     :spec v1beta1/subject-data}))