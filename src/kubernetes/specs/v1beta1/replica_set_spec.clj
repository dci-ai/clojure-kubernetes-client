(ns kubernetes.specs.v1beta1/replica-set-spec
  (:require [clojure.spec.alpha :as s]
            [spec-tools.data-spec :as ds]
            [kubernetes.specs.v1/label-selector :refer :all]
            [kubernetes.specs.v1/pod-template-spec :refer :all]
            )
  (:import (java.io File)))


(def v1beta1/replica-set-spec-data
  {
   (ds/opt :minReadySeconds) int?
   (ds/opt :replicas) int?
   (ds/opt :selector) v1/label-selector-spec
   (ds/opt :template) v1/pod-template-spec-spec
   })

(def v1beta1/replica-set-spec-spec
  (ds/spec
    {:name ::v1beta1/replica-set-spec
     :spec v1beta1/replica-set-spec-data}))
