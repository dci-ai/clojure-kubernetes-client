(ns kubernetes.specs.v1beta1/subject-access-review-status
  (:require [clojure.spec.alpha :as s]
            [spec-tools.data-spec :as ds]
            )
  (:import (java.io File)))


(def v1beta1/subject-access-review-status-data
  {
   (ds/req :allowed) boolean?
   (ds/opt :evaluationError) string?
   (ds/opt :reason) string?
   })

(def v1beta1/subject-access-review-status-spec
  (ds/spec
    {:name ::v1beta1/subject-access-review-status
     :spec v1beta1/subject-access-review-status-data}))
