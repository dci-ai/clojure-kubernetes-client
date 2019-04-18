(ns kubernetes.specs.v1beta1/ingress-tls
  (:require [clojure.spec.alpha :as s]
            [spec-tools.data-spec :as ds]
            )
  (:import (java.io File)))


(def v1beta1/ingress-tls-data
  {
   (ds/opt :hosts) (s/coll-of string?)
   (ds/opt :secretName) string?
   })

(def v1beta1/ingress-tls-spec
  (ds/spec
    {:name ::v1beta1/ingress-tls
     :spec v1beta1/ingress-tls-data}))
