(ns kubernetes.specs.v1/container-state-running
  (:require [clojure.spec.alpha :as s]
            [spec-tools.data-spec :as ds]
            )
  (:import (java.io File)))


(def v1/container-state-running-data
  {
   (ds/opt :startedAt) inst?
   })

(def v1/container-state-running-spec
  (ds/spec
    {:name ::v1/container-state-running
     :spec v1/container-state-running-data}))
