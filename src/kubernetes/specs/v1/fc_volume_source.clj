(ns kubernetes.specs.v1/fc-volume-source
  (:require [clojure.spec.alpha :as s]
            [spec-tools.data-spec :as ds]
            )
  (:import (java.io File)))


(def v1/fc-volume-source-data
  {
   (ds/opt :fsType) string?
   (ds/opt :lun) int?
   (ds/opt :readOnly) boolean?
   (ds/opt :targetWWNs) (s/coll-of string?)
   (ds/opt :wwids) (s/coll-of string?)
   })

(def v1/fc-volume-source-spec
  (ds/spec
    {:name ::v1/fc-volume-source
     :spec v1/fc-volume-source-data}))
