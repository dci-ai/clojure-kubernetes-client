(ns kubernetes.specs.apps/v1beta1/scale-spec
  (:require [clojure.spec.alpha :as s]
            [spec-tools.data-spec :as ds]
            )
  (:import (java.io File)))


(def apps/v1beta1/scale-spec-data
  {
   (ds/opt :replicas) int?
   })

(def apps/v1beta1/scale-spec-spec
  (ds/spec
    {:name ::apps/v1beta1/scale-spec
     :spec apps/v1beta1/scale-spec-data}))
