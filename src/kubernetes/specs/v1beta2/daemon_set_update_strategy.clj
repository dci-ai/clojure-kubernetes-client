(ns kubernetes.specs.v1beta2/daemon-set-update-strategy
  (:require [clojure.spec.alpha :as s]
            [spec-tools.data-spec :as ds]
            [kubernetes.specs.v1beta2/rolling-update-daemon-set :refer :all]
            )
  (:import (java.io File)))


(def v1beta2/daemon-set-update-strategy-data
  {
   (ds/opt :rollingUpdate) v1beta2/rolling-update-daemon-set-spec
   (ds/opt :type) string?
   })

(def v1beta2/daemon-set-update-strategy-spec
  (ds/spec
    {:name ::v1beta2/daemon-set-update-strategy
     :spec v1beta2/daemon-set-update-strategy-data}))
