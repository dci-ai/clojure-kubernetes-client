(ns kubernetes.api.extensions-v-beta-
  (:require [kubernetes.core :refer [call-api check-required-params with-collection-format *api-context*]]
            [clojure.spec.alpha :as s]
            [spec-tools.core :as st]
            [orchestra.core :refer [defn-spec]]
            [kubernetes.specs.v1/node-config-source :refer :all]
            [kubernetes.specs.v1/network-policy-peer :refer :all]
            [kubernetes.specs.v1/pod-anti-affinity :refer :all]
            [kubernetes.specs.v1/scale-io-volume-source :refer :all]
            [kubernetes.specs.v1/daemon-endpoint :refer :all]
            [kubernetes.specs.v1/env-var-source :refer :all]
            [kubernetes.specs.v1beta1/api-service-condition :refer :all]
            [kubernetes.specs.v1beta1/daemon-set-spec :refer :all]
            [kubernetes.specs.v2alpha1/cron-job-status :refer :all]
            [kubernetes.specs.v1/replication-controller-condition :refer :all]
            [kubernetes.specs.v1/volume-mount :refer :all]
            [kubernetes.specs.extensions/v1beta1/rolling-update-deployment :refer :all]
            [kubernetes.specs.v1/resource-field-selector :refer :all]
            [kubernetes.specs.v1/component-condition :refer :all]
            [kubernetes.specs.v1/scale :refer :all]
            [kubernetes.specs.v1/network-policy-ingress-rule :refer :all]
            [kubernetes.specs.v1/binding :refer :all]
            [kubernetes.specs.v1/preconditions :refer :all]
            [kubernetes.specs.v1/initializers :refer :all]
            [kubernetes.specs.v1/pod-template-list :refer :all]
            [kubernetes.specs.v1/toleration :refer :all]
            [kubernetes.specs.v1/node-spec :refer :all]
            [kubernetes.specs.v1/object-meta :refer :all]
            [kubernetes.specs.v1alpha1/role-list :refer :all]
            [kubernetes.specs.v1beta1/pod-disruption-budget :refer :all]
            [kubernetes.specs.v1/role-binding :refer :all]
            [kubernetes.specs.v1/user-info :refer :all]
            [kubernetes.specs.extensions/v1beta1/deployment-strategy :refer :all]
            [kubernetes.specs.v1/api-group-list :refer :all]
            [kubernetes.specs.v1beta1/role :refer :all]
            [kubernetes.specs.v1/downward-api-volume-file :refer :all]
            [kubernetes.specs.v1beta1/cluster-role :refer :all]
            [kubernetes.specs.v1/config-map-projection :refer :all]
            [kubernetes.specs.v1beta1/ingress-list :refer :all]
            [kubernetes.specs.v1beta2/stateful-set-status :refer :all]
            [kubernetes.specs.apps/v1beta1/deployment-strategy :refer :all]
            [kubernetes.specs.v1beta1/api-service-list :refer :all]
            [kubernetes.specs.v1/network-policy :refer :all]
            [kubernetes.specs.v1/iscsi-volume-source :refer :all]
            [kubernetes.specs.apps/v1beta1/rollback-config :refer :all]
            [kubernetes.specs.v1/node-status :refer :all]
            [kubernetes.specs.v1/subject :refer :all]
            [kubernetes.specs.v1/cluster-role-list :refer :all]
            [kubernetes.specs.v1beta1/id-range :refer :all]
            [kubernetes.specs.v1/cinder-volume-source :refer :all]
            [kubernetes.specs.v1/namespace-list :refer :all]
            [kubernetes.specs.v1/local-subject-access-review :refer :all]
            [kubernetes.specs.v1/limit-range :refer :all]
            [kubernetes.specs.v1/limit-range-list :refer :all]
            [kubernetes.specs.v1/config-map :refer :all]
            [kubernetes.specs.v1beta1/ingress-backend :refer :all]
            [kubernetes.specs.v1beta2/replica-set-status :refer :all]
            [kubernetes.specs.v1/load-balancer-ingress :refer :all]
            [kubernetes.specs.v1/pod-affinity :refer :all]
            [kubernetes.specs.v1beta2/stateful-set-spec :refer :all]
            [kubernetes.specs.v1/pod :refer :all]
            [kubernetes.specs.v1/horizontal-pod-autoscaler-list :refer :all]
            [kubernetes.specs.v1/persistent-volume :refer :all]
            [kubernetes.specs.v2beta1/horizontal-pod-autoscaler :refer :all]
            [kubernetes.specs.v1/service-list :refer :all]
            [kubernetes.specs.v1beta1/job-template-spec :refer :all]
            [kubernetes.specs.v1beta1/custom-resource-validation :refer :all]
            [kubernetes.specs.v1beta1/ip-block :refer :all]
            [kubernetes.specs.v1/namespace-spec :refer :all]
            [kubernetes.specs.v1/persistent-volume-list :refer :all]
            [kubernetes.specs.v1beta1/non-resource-attributes :refer :all]
            [kubernetes.specs.v1/photon-persistent-disk-volume-source :refer :all]
            [kubernetes.specs.v1/endpoints :refer :all]
            [kubernetes.specs.v1beta1/network-policy-egress-rule :refer :all]
            [kubernetes.specs.v1beta1/allowed-host-path :refer :all]
            [kubernetes.specs.v1/service :refer :all]
            [kubernetes.specs.v1/session-affinity-config :refer :all]
            [kubernetes.specs.v1/status :refer :all]
            [kubernetes.specs.v1alpha1/rule-with-operations :refer :all]
            [kubernetes.specs.v1/self-subject-access-review :refer :all]
            [kubernetes.specs.v1beta2/deployment :refer :all]
            [kubernetes.specs.v1/watch-event :refer :all]
            [kubernetes.specs.v1beta1/fs-group-strategy-options :refer :all]
            [kubernetes.specs.v1/cross-version-object-reference :refer :all]
            [kubernetes.specs.v1beta1/daemon-set-update-strategy :refer :all]
            [kubernetes.specs.version/info :refer :all]
            [kubernetes.specs.v1beta1/json :refer :all]
            [kubernetes.specs.v1/gce-persistent-disk-volume-source :refer :all]
            [kubernetes.specs.v1/job-list :refer :all]
            [kubernetes.specs.v1/container-state-waiting :refer :all]
            [kubernetes.specs.v1/scale-io-persistent-volume-source :refer :all]
            [kubernetes.specs.v1beta1/api-service-status :refer :all]
            [kubernetes.specs.v1/pod-condition :refer :all]
            [kubernetes.specs.v2beta1/horizontal-pod-autoscaler-list :refer :all]
            [kubernetes.specs.v1beta2/replica-set-spec :refer :all]
            [kubernetes.specs.v1beta1/custom-resource-definition-names :refer :all]
            [kubernetes.specs.v1beta1/replica-set-status :refer :all]
            [kubernetes.specs.v1beta1/local-subject-access-review :refer :all]
            [kubernetes.specs.v1beta1/json-schema-props-or-bool :refer :all]
            [kubernetes.specs.v1/secret-list :refer :all]
            [kubernetes.specs.v1/node-list :refer :all]
            [kubernetes.specs.v1/pod-template-spec :refer :all]
            [kubernetes.specs.v1/secret-reference :refer :all]
            [kubernetes.specs.v1/api-group :refer :all]
            [kubernetes.specs.v1beta2/rolling-update-deployment :refer :all]
            [kubernetes.specs.v1beta1/http-ingress-rule-value :refer :all]
            [kubernetes.specs.v1/env-var :refer :all]
            [kubernetes.specs.v1alpha1/policy-rule :refer :all]
            [kubernetes.specs.v2beta1/cross-version-object-reference :refer :all]
            [kubernetes.specs.v1/pod-spec :refer :all]
            [kubernetes.specs.v1beta1/subject-access-review :refer :all]
            [kubernetes.specs.v1beta1/certificate-signing-request-condition :refer :all]
            [kubernetes.specs.v1/pod-status :refer :all]
            [kubernetes.specs.v1beta1/certificate-signing-request-status :refer :all]
            [kubernetes.specs.v1/vsphere-virtual-disk-volume-source :refer :all]
            [kubernetes.specs.v1/token-review-spec :refer :all]
            [kubernetes.specs.v1/storage-class :refer :all]
            [kubernetes.specs.v1beta1/cluster-role-binding-list :refer :all]
            [kubernetes.specs.v1beta1/host-port-range :refer :all]
            [kubernetes.specs.v1beta2/controller-revision-list :refer :all]
            [kubernetes.specs.extensions/v1beta1/scale-spec :refer :all]
            [kubernetes.specs.v2beta1/horizontal-pod-autoscaler-status :refer :all]
            [kubernetes.specs.v1beta1/ingress :refer :all]
            [kubernetes.specs.v1alpha1/pod-preset-list :refer :all]
            [kubernetes.specs.v1/service-account :refer :all]
            [kubernetes.specs.extensions/v1beta1/deployment :refer :all]
            [kubernetes.specs.v1beta1/daemon-set :refer :all]
            [kubernetes.specs.v1/service-port :refer :all]
            [kubernetes.specs.v1alpha1/priority-class :refer :all]
            [kubernetes.specs.v1beta1/api-service :refer :all]
            [kubernetes.specs.v1beta1/replica-set-condition :refer :all]
            [kubernetes.specs.v1beta1/daemon-set-list :refer :all]
            [kubernetes.specs.extensions/v1beta1/rollback-config :refer :all]
            [kubernetes.specs.apps/v1beta1/deployment-spec :refer :all]
            [kubernetes.specs.v1/namespace :refer :all]
            [kubernetes.specs.v1/resource-quota-spec :refer :all]
            [kubernetes.specs.v1/flex-volume-source :refer :all]
            [kubernetes.specs.v1beta1/se-linux-strategy-options :refer :all]
            [kubernetes.specs.v1beta1/eviction :refer :all]
            [kubernetes.specs.v1/exec-action :refer :all]
            [kubernetes.specs.v1/service-status :refer :all]
            [kubernetes.specs.v1/node-selector :refer :all]
            [kubernetes.specs.v1/resource-quota :refer :all]
            [kubernetes.specs.v1/limit-range-item :refer :all]
            [kubernetes.specs.v1/secret :refer :all]
            [kubernetes.specs.v1beta2/deployment-condition :refer :all]
            [kubernetes.specs.v1/azure-file-persistent-volume-source :refer :all]
            [kubernetes.specs.v1/scale-spec :refer :all]
            [kubernetes.specs.v1/handler :refer :all]
            [kubernetes.specs.v1beta2/scale-spec :refer :all]
            [kubernetes.specs.v1/resource-attributes :refer :all]
            [kubernetes.specs.v1/capabilities :refer :all]
            [kubernetes.specs.v1beta1/self-subject-rules-review-spec :refer :all]
            [kubernetes.specs.v1/token-review :refer :all]
            [kubernetes.specs.v1/container :refer :all]
            [kubernetes.specs.v1beta1/json-schema-props-or-string-array :refer :all]
            [kubernetes.specs.v1/self-subject-access-review-spec :refer :all]
            [kubernetes.specs.v1/resource-quota-status :refer :all]
            [kubernetes.specs.v1alpha1/cluster-role-binding-list :refer :all]
            [kubernetes.specs.v1/preferred-scheduling-term :refer :all]
            [kubernetes.specs.v1beta1/api-service-spec :refer :all]
            [kubernetes.specs.v1/label-selector :refer :all]
            [kubernetes.specs.v1/self-subject-rules-review :refer :all]
            [kubernetes.specs.v1/scale-status :refer :all]
            [kubernetes.specs.v1beta1/certificate-signing-request-list :refer :all]
            [kubernetes.specs.v1/azure-disk-volume-source :refer :all]
            [kubernetes.specs.extensions/v1beta1/deployment-spec :refer :all]
            [kubernetes.specs.v1alpha1/role-ref :refer :all]
            [kubernetes.specs.v1/rbd-volume-source :refer :all]
            [kubernetes.specs.v1beta1/resource-rule :refer :all]
            [kubernetes.specs.v1alpha1/cluster-role-list :refer :all]
            [kubernetes.specs.v1beta2/daemon-set-update-strategy :refer :all]
            [kubernetes.specs.v1/taint :refer :all]
            [kubernetes.specs.v1/pod-template :refer :all]
            [kubernetes.specs.v1beta1/http-ingress-path :refer :all]
            [kubernetes.specs.v1beta2/daemon-set-status :refer :all]
            [kubernetes.specs.apps/v1beta1/scale :refer :all]
            [kubernetes.specs.v1/downward-api-volume-source :refer :all]
            [kubernetes.specs.v1beta1/stateful-set-spec :refer :all]
            [kubernetes.specs.v1/se-linux-options :refer :all]
            [kubernetes.specs.v1/storage-os-volume-source :refer :all]
            [kubernetes.specs.v1beta2/rolling-update-stateful-set-strategy :refer :all]
            [kubernetes.specs.v1alpha1/rule :refer :all]
            [kubernetes.specs.v1beta1/cron-job :refer :all]
            [kubernetes.specs.v1beta2/stateful-set-list :refer :all]
            [kubernetes.specs.v1/container-state :refer :all]
            [kubernetes.specs.v1beta1/token-review-spec :refer :all]
            [kubernetes.specs.v1/node :refer :all]
            [kubernetes.specs.v1/http-header :refer :all]
            [kubernetes.specs.v1beta1/subject-access-review-status :refer :all]
            [kubernetes.specs.v1beta1/network-policy-spec :refer :all]
            [kubernetes.specs.v1/subject-rules-review-status :refer :all]
            [kubernetes.specs.v1beta1/service-reference :refer :all]
            [kubernetes.specs.v1/projected-volume-source :refer :all]
            [kubernetes.specs.v1/persistent-volume-spec :refer :all]
            [kubernetes.specs.v1beta1/stateful-set-list :refer :all]
            [kubernetes.specs.v2beta1/horizontal-pod-autoscaler-spec :refer :all]
            [kubernetes.specs.v1/api-resource :refer :all]
            [kubernetes.specs.v1/local-object-reference :refer :all]
            [kubernetes.specs.v1/limit-range-spec :refer :all]
            [kubernetes.specs.v1beta1/cluster-role-list :refer :all]
            [kubernetes.specs.v1alpha1/pod-preset :refer :all]
            [kubernetes.specs.v1/network-policy-egress-rule :refer :all]
            [kubernetes.specs.v1/replication-controller-status :refer :all]
            [kubernetes.specs.v1beta1/ingress-spec :refer :all]
            [kubernetes.specs.v1/non-resource-attributes :refer :all]
            [kubernetes.specs.v1alpha1/service-reference :refer :all]
            [kubernetes.specs.v1/node-selector-term :refer :all]
            [kubernetes.specs.v1beta1/storage-class-list :refer :all]
            [kubernetes.specs.v1/service-spec :refer :all]
            [kubernetes.specs.v1beta1/certificate-signing-request-spec :refer :all]
            [kubernetes.specs.v1/node-address :refer :all]
            [kubernetes.specs.v1/quobyte-volume-source :refer :all]
            [kubernetes.specs.v1/container-status :refer :all]
            [kubernetes.specs.v1/container-state-terminated :refer :all]
            [kubernetes.specs.v1beta1/custom-resource-definition-spec :refer :all]
            [kubernetes.specs.v1/persistent-volume-claim-status :refer :all]
            [kubernetes.specs.v1/event :refer :all]
            [kubernetes.specs.v1beta1/resource-attributes :refer :all]
            [kubernetes.specs.v1alpha1/external-admission-hook-configuration :refer :all]
            [kubernetes.specs.v1/container-image :refer :all]
            [kubernetes.specs.v1/object-reference :refer :all]
            [kubernetes.specs.v1beta1/custom-resource-definition-condition :refer :all]
            [kubernetes.specs.v1/azure-file-volume-source :refer :all]
            [kubernetes.specs.v1/job-spec :refer :all]
            [kubernetes.specs.v1beta2/rolling-update-daemon-set :refer :all]
            [kubernetes.specs.v1/key-to-path :refer :all]
            [kubernetes.specs.v1beta1/daemon-set-status :refer :all]
            [kubernetes.specs.v1/git-repo-volume-source :refer :all]
            [kubernetes.specs.v2beta1/resource-metric-source :refer :all]
            [kubernetes.specs.v1/node-system-info :refer :all]
            [kubernetes.specs.v1/config-map-list :refer :all]
            [kubernetes.specs.v1beta2/replica-set-list :refer :all]
            [kubernetes.specs.v1alpha1/initializer-configuration :refer :all]
            [kubernetes.specs.v1/http-get-action :refer :all]
            [kubernetes.specs.v1beta1/ingress-status :refer :all]
            [kubernetes.specs.apps/v1beta1/deployment-rollback :refer :all]
            [kubernetes.specs.v1/horizontal-pod-autoscaler-spec :refer :all]
            [kubernetes.specs.v1beta1/controller-revision :refer :all]
            [kubernetes.specs.v1/replication-controller-list :refer :all]
            [kubernetes.specs.v1beta1/subject-rules-review-status :refer :all]
            [kubernetes.specs.v1beta1/controller-revision-list :refer :all]
            [kubernetes.specs.v2beta1/resource-metric-status :refer :all]
            [kubernetes.specs.apps/v1beta1/rolling-update-deployment :refer :all]
            [kubernetes.specs.extensions/v1beta1/deployment-status :refer :all]
            [kubernetes.specs.v1/weighted-pod-affinity-term :refer :all]
            [kubernetes.specs.v1beta2/daemon-set :refer :all]
            [kubernetes.specs.extensions/v1beta1/deployment-condition :refer :all]
            [kubernetes.specs.v2alpha1/cron-job-list :refer :all]
            [kubernetes.specs.v1/job :refer :all]
            [kubernetes.specs.v1/secret-volume-source :refer :all]
            [kubernetes.specs.v1/server-address-by-client-cidr :refer :all]
            [kubernetes.specs.v1/node-daemon-endpoints :refer :all]
            [kubernetes.specs.v1alpha1/admission-hook-client-config :refer :all]
            [kubernetes.specs.v1beta1/custom-resource-definition :refer :all]
            [kubernetes.specs.v1/pod-security-context :refer :all]
            [kubernetes.specs.v1/affinity :refer :all]
            [kubernetes.specs.v2beta1/metric-status :refer :all]
            [kubernetes.specs.v1beta1/ingress-tls :refer :all]
            [kubernetes.specs.v1/empty-dir-volume-source :refer :all]
            [kubernetes.specs.v1/fc-volume-source :refer :all]
            [kubernetes.specs.v1/resource-quota-list :refer :all]
            [kubernetes.specs.v1/list-meta :refer :all]
            [kubernetes.specs.v1/api-resource-list :refer :all]
            [kubernetes.specs.v1/horizontal-pod-autoscaler-status :refer :all]
            [kubernetes.specs.v1beta1/json-schema-props-or-array :refer :all]
            [kubernetes.specs.v1/component-status :refer :all]
            [kubernetes.specs.v1beta1/cron-job-spec :refer :all]
            [kubernetes.specs.v1/subject-access-review :refer :all]
            [kubernetes.specs.v1/config-map-volume-source :refer :all]
            [kubernetes.specs.v1/secret-key-selector :refer :all]
            [kubernetes.specs.v1/tcp-socket-action :refer :all]
            [kubernetes.specs.v1/label-selector-requirement :refer :all]
            [kubernetes.specs.v1alpha1/initializer :refer :all]
            [kubernetes.specs.v1/storage-class-list :refer :all]
            [kubernetes.specs.v1/status-cause :refer :all]
            [kubernetes.specs.v1beta1/non-resource-rule :refer :all]
            [kubernetes.specs.v1/cluster-role :refer :all]
            [kubernetes.specs.v1/persistent-volume-status :refer :all]
            [kubernetes.specs.v1/host-alias :refer :all]
            [kubernetes.specs.v1/client-ip-config :refer :all]
            [kubernetes.specs.v1/volume-projection :refer :all]
            [kubernetes.specs.apps/v1beta1/scale-spec :refer :all]
            [kubernetes.specs.v1beta2/daemon-set-list :refer :all]
            [kubernetes.specs.v1beta1/replica-set-spec :refer :all]
            [kubernetes.specs.v1beta2/replica-set :refer :all]
            [kubernetes.specs.v1beta1/replica-set :refer :all]
            [kubernetes.specs.v1/subject-access-review-status :refer :all]
            [kubernetes.specs.v1/namespace-status :refer :all]
            [kubernetes.specs.v1/policy-rule :refer :all]
            [kubernetes.specs.v1beta2/deployment-spec :refer :all]
            [kubernetes.specs.v1/aws-elastic-block-store-volume-source :refer :all]
            [kubernetes.specs.v1beta1/pod-disruption-budget-status :refer :all]
            [kubernetes.specs.extensions/v1beta1/deployment-list :refer :all]
            [kubernetes.specs.v1/local-volume-source :refer :all]
            [kubernetes.specs.v1beta1/stateful-set :refer :all]
            [kubernetes.specs.v1beta1/cluster-role-binding :refer :all]
            [kubernetes.specs.v1beta1/pod-disruption-budget-list :refer :all]
            [kubernetes.specs.v1beta1/role-list :refer :all]
            [kubernetes.specs.v1/persistent-volume-claim :refer :all]
            [kubernetes.specs.v2alpha1/job-template-spec :refer :all]
            [kubernetes.specs.v1beta1/role-binding-list :refer :all]
            [kubernetes.specs.apps/v1beta1/deployment :refer :all]
            [kubernetes.specs.v2alpha1/cron-job :refer :all]
            [kubernetes.specs.v1/replication-controller :refer :all]
            [kubernetes.specs.v1beta1/pod-security-policy-list :refer :all]
            [kubernetes.specs.v2beta1/metric-spec :refer :all]
            [kubernetes.specs.v1/endpoint-subset :refer :all]
            [kubernetes.specs.v1beta1/role-ref :refer :all]
            [kubernetes.specs.v1/glusterfs-volume-source :refer :all]
            [kubernetes.specs.v1beta1/network-policy-list :refer :all]
            [kubernetes.specs.v1/network-policy-spec :refer :all]
            [kubernetes.specs.v1/resource-requirements :refer :all]
            [kubernetes.specs.apps/v1beta1/scale-status :refer :all]
            [kubernetes.specs.v1/storage-os-persistent-volume-source :refer :all]
            [kubernetes.specs.v1beta1/run-as-user-strategy-options :refer :all]
            [kubernetes.specs.v1beta2/daemon-set-spec :refer :all]
            [kubernetes.specs.v1/ceph-fs-volume-source :refer :all]
            [kubernetes.specs.v1/volume :refer :all]
            [kubernetes.specs.extensions/v1beta1/scale :refer :all]
            [kubernetes.specs.apps/v1beta1/deployment-status :refer :all]
            [kubernetes.specs.v1beta1/replica-set-list :refer :all]
            [kubernetes.specs.v1beta1/storage-class :refer :all]
            [kubernetes.specs.v1beta1/network-policy-port :refer :all]
            [kubernetes.specs.v1alpha1/role-binding-list :refer :all]
            [kubernetes.specs.v1beta1/custom-resource-definition-status :refer :all]
            [kubernetes.specs.v1/subject-access-review-spec :refer :all]
            [kubernetes.specs.v1/api-versions :refer :all]
            [kubernetes.specs.v1/replication-controller-spec :refer :all]
            [kubernetes.specs.v1beta1/network-policy :refer :all]
            [kubernetes.specs.v1/container-port :refer :all]
            [kubernetes.specs.v1/cluster-role-binding :refer :all]
            [kubernetes.specs.v1/config-map-key-selector :refer :all]
            [kubernetes.specs.v1beta1/json-schema-props :refer :all]
            [kubernetes.specs.v1/job-status :refer :all]
            [kubernetes.specs.v1/object-field-selector :refer :all]
            [kubernetes.specs.v1/node-condition :refer :all]
            [kubernetes.specs.v1/config-map-env-source :refer :all]
            [kubernetes.specs.v1beta1/custom-resource-definition-list :refer :all]
            [kubernetes.specs.v1beta1/supplemental-groups-strategy-options :refer :all]
            [kubernetes.specs.v1/persistent-volume-claim-spec :refer :all]
            [kubernetes.specs.v1/delete-options :refer :all]
            [kubernetes.specs.v1beta1/ingress-rule :refer :all]
            [kubernetes.specs.v1/attached-volume :refer :all]
            [kubernetes.specs.v1/pod-affinity-term :refer :all]
            [kubernetes.specs.v1beta1/self-subject-rules-review :refer :all]
            [kubernetes.specs.v1alpha1/role-binding :refer :all]
            [kubernetes.specs.v1alpha1/cluster-role-binding :refer :all]
            [kubernetes.specs.v1/role :refer :all]
            [kubernetes.specs.extensions/v1beta1/scale-status :refer :all]
            [kubernetes.specs.v1beta2/replica-set-condition :refer :all]
            [kubernetes.specs.v1/endpoints-list :refer :all]
            [kubernetes.specs.v1beta2/scale :refer :all]
            [kubernetes.specs.v1alpha1/role :refer :all]
            [kubernetes.specs.v1/security-context :refer :all]
            [kubernetes.specs.v1beta2/controller-revision :refer :all]
            [kubernetes.specs.v1beta2/deployment-list :refer :all]
            [kubernetes.specs.v1beta1/cron-job-status :refer :all]
            [kubernetes.specs.v2alpha1/cron-job-spec :refer :all]
            [kubernetes.specs.v1/group-version-for-discovery :refer :all]
            [kubernetes.specs.v1/endpoint-port :refer :all]
            [kubernetes.specs.v1/nfs-volume-source :refer :all]
            [kubernetes.specs.v1alpha1/initializer-configuration-list :refer :all]
            [kubernetes.specs.v1/self-subject-rules-review-spec :refer :all]
            [kubernetes.specs.v1/job-condition :refer :all]
            [kubernetes.specs.v1beta1/token-review-status :refer :all]
            [kubernetes.specs.runtime/raw-extension :refer :all]
            [kubernetes.specs.v1/initializer :refer :all]
            [kubernetes.specs.v1/node-selector-requirement :refer :all]
            [kubernetes.specs.v1/env-from-source :refer :all]
            [kubernetes.specs.v1/ceph-fs-persistent-volume-source :refer :all]
            [kubernetes.specs.v1/node-affinity :refer :all]
            [kubernetes.specs.v1beta1/policy-rule :refer :all]
            [kubernetes.specs.v1/token-review-status :refer :all]
            [kubernetes.specs.v1/event-list :refer :all]
            [kubernetes.specs.v1/resource-rule :refer :all]
            [kubernetes.specs.v1/service-account-list :refer :all]
            [kubernetes.specs.v1/event-source :refer :all]
            [kubernetes.specs.v1/status-details :refer :all]
            [kubernetes.specs.v1/persistent-volume-claim-condition :refer :all]
            [kubernetes.specs.v1beta1/user-info :refer :all]
            [kubernetes.specs.v1/cluster-role-binding-list :refer :all]
            [kubernetes.specs.v1beta1/stateful-set-status :refer :all]
            [kubernetes.specs.v1/role-list :refer :all]
            [kubernetes.specs.v1/component-status-list :refer :all]
            [kubernetes.specs.v1alpha1/cluster-role :refer :all]
            [kubernetes.specs.v1/probe :refer :all]
            [kubernetes.specs.v1/container-state-running :refer :all]
            [kubernetes.specs.v1/load-balancer-status :refer :all]
            [kubernetes.specs.v1alpha1/priority-class-list :refer :all]
            [kubernetes.specs.v1/host-path-volume-source :refer :all]
            [kubernetes.specs.v1beta2/scale-status :refer :all]
            [kubernetes.specs.v1/persistent-volume-claim-volume-source :refer :all]
            [kubernetes.specs.v1/role-binding-list :refer :all]
            [kubernetes.specs.v1/pod-list :refer :all]
            [kubernetes.specs.v2beta1/horizontal-pod-autoscaler-condition :refer :all]
            [kubernetes.specs.v1/horizontal-pod-autoscaler :refer :all]
            [kubernetes.specs.v1beta2/stateful-set-update-strategy :refer :all]
            [kubernetes.specs.v1/portworx-volume-source :refer :all]
            [kubernetes.specs.v1/secret-projection :refer :all]
            [kubernetes.specs.apps/v1beta1/deployment-condition :refer :all]
            [kubernetes.specs.v1beta1/self-subject-access-review :refer :all]
            [kubernetes.specs.apps/v1beta1/deployment-list :refer :all]
            [kubernetes.specs.v1beta1/subject :refer :all]
            [kubernetes.specs.v1/secret-env-source :refer :all]
            [kubernetes.specs.v1beta2/deployment-strategy :refer :all]
            [kubernetes.specs.v1beta1/certificate-signing-request :refer :all]
            [kubernetes.specs.v1beta1/external-documentation :refer :all]
            [kubernetes.specs.v1/downward-api-projection :refer :all]
            [kubernetes.specs.v1beta1/pod-security-policy-spec :refer :all]
            [kubernetes.specs.v2beta1/pods-metric-status :refer :all]
            [kubernetes.specs.v1/role-ref :refer :all]
            [kubernetes.specs.v1beta1/role-binding :refer :all]
            [kubernetes.specs.v1beta1/token-review :refer :all]
            [kubernetes.specs.v1/network-policy-list :refer :all]
            [kubernetes.specs.v1/lifecycle :refer :all]
            [kubernetes.specs.v2beta1/object-metric-status :refer :all]
            [kubernetes.specs.v1beta1/rolling-update-daemon-set :refer :all]
            [kubernetes.specs.extensions/v1beta1/deployment-rollback :refer :all]
            [kubernetes.specs.v1/endpoint-address :refer :all]
            [kubernetes.specs.v1beta1/cron-job-list :refer :all]
            [kubernetes.specs.v1alpha1/pod-preset-spec :refer :all]
            [kubernetes.specs.v1beta1/subject-access-review-spec :refer :all]
            [kubernetes.specs.v1beta1/pod-security-policy :refer :all]
            [kubernetes.specs.v2beta1/pods-metric-source :refer :all]
            [kubernetes.specs.v1/owner-reference :refer :all]
            [kubernetes.specs.v1beta1/rolling-update-stateful-set-strategy :refer :all]
            [kubernetes.specs.v1beta2/deployment-status :refer :all]
            [kubernetes.specs.v1beta1/network-policy-peer :refer :all]
            [kubernetes.specs.v1beta1/network-policy-ingress-rule :refer :all]
            [kubernetes.specs.v1beta1/pod-disruption-budget-spec :refer :all]
            [kubernetes.specs.v1beta1/self-subject-access-review-spec :refer :all]
            [kubernetes.specs.v1/non-resource-rule :refer :all]
            [kubernetes.specs.v1/network-policy-port :refer :all]
            [kubernetes.specs.v1/persistent-volume-claim-list :refer :all]
            [kubernetes.specs.v1beta1/stateful-set-update-strategy :refer :all]
            [kubernetes.specs.v1alpha1/subject :refer :all]
            [kubernetes.specs.v1/flocker-volume-source :refer :all]
            [kubernetes.specs.v2beta1/object-metric-source :refer :all]
            [kubernetes.specs.v1beta2/stateful-set :refer :all]
            [kubernetes.specs.v1/ip-block :refer :all]
            [kubernetes.specs.v1alpha1/external-admission-hook :refer :all]
            [kubernetes.specs.v1alpha1/external-admission-hook-configuration-list :refer :all]
            )
  (:import (java.io File)))


(defn-spec create-namespaced-daemon-set-with-http-info any?
  "
  create a DaemonSet"
  ([namespace string?, body v1beta1/daemon-set, ] (create-namespaced-daemon-set-with-http-info namespace body nil))
  ([namespace string?, body v1beta1/daemon-set, {:keys [pretty]} (s/map-of keyword? any?)]
   (check-required-params namespace body)
   (call-api "/apis/extensions/v1beta1/namespaces/{namespace}/daemonsets" :post
             {:path-params   {"namespace" namespace }
              :header-params {}
              :query-params  {"pretty" pretty }
              :form-params   {}
              :body-param    body
              :content-types []
              :accepts       ["application/json" "application/yaml" "application/vnd.kubernetes.protobuf"]
              :auth-names    ["BearerToken"]})))

(defn-spec create-namespaced-daemon-set v1beta1/daemon-set-spec
  "
  create a DaemonSet"
  ([namespace string?, body v1beta1/daemon-set, ] (create-namespaced-daemon-set namespace body nil))
  ([namespace string?, body v1beta1/daemon-set, optional-params any?]
   (let [res (:data (create-namespaced-daemon-set-with-http-info namespace body optional-params))]
     (if (:decode-models *api-context*)
        (st/decode v1beta1/daemon-set-spec res st/string-transformer)
        res))))


(defn-spec create-namespaced-deployment-with-http-info any?
  "
  create a Deployment"
  ([namespace string?, body extensions/v1beta1/deployment, ] (create-namespaced-deployment-with-http-info namespace body nil))
  ([namespace string?, body extensions/v1beta1/deployment, {:keys [pretty]} (s/map-of keyword? any?)]
   (check-required-params namespace body)
   (call-api "/apis/extensions/v1beta1/namespaces/{namespace}/deployments" :post
             {:path-params   {"namespace" namespace }
              :header-params {}
              :query-params  {"pretty" pretty }
              :form-params   {}
              :body-param    body
              :content-types []
              :accepts       ["application/json" "application/yaml" "application/vnd.kubernetes.protobuf"]
              :auth-names    ["BearerToken"]})))

(defn-spec create-namespaced-deployment extensions/v1beta1/deployment-spec
  "
  create a Deployment"
  ([namespace string?, body extensions/v1beta1/deployment, ] (create-namespaced-deployment namespace body nil))
  ([namespace string?, body extensions/v1beta1/deployment, optional-params any?]
   (let [res (:data (create-namespaced-deployment-with-http-info namespace body optional-params))]
     (if (:decode-models *api-context*)
        (st/decode extensions/v1beta1/deployment-spec res st/string-transformer)
        res))))


(defn-spec create-namespaced-deployment-rollback-with-http-info any?
  "
  create rollback of a Deployment"
  ([name string?, namespace string?, body extensions/v1beta1/deployment-rollback, ] (create-namespaced-deployment-rollback-with-http-info name namespace body nil))
  ([name string?, namespace string?, body extensions/v1beta1/deployment-rollback, {:keys [pretty]} (s/map-of keyword? any?)]
   (check-required-params name namespace body)
   (call-api "/apis/extensions/v1beta1/namespaces/{namespace}/deployments/{name}/rollback" :post
             {:path-params   {"name" name "namespace" namespace }
              :header-params {}
              :query-params  {"pretty" pretty }
              :form-params   {}
              :body-param    body
              :content-types []
              :accepts       ["application/json" "application/yaml" "application/vnd.kubernetes.protobuf"]
              :auth-names    ["BearerToken"]})))

(defn-spec create-namespaced-deployment-rollback extensions/v1beta1/deployment-rollback-spec
  "
  create rollback of a Deployment"
  ([name string?, namespace string?, body extensions/v1beta1/deployment-rollback, ] (create-namespaced-deployment-rollback name namespace body nil))
  ([name string?, namespace string?, body extensions/v1beta1/deployment-rollback, optional-params any?]
   (let [res (:data (create-namespaced-deployment-rollback-with-http-info name namespace body optional-params))]
     (if (:decode-models *api-context*)
        (st/decode extensions/v1beta1/deployment-rollback-spec res st/string-transformer)
        res))))


(defn-spec create-namespaced-ingress-with-http-info any?
  "
  create an Ingress"
  ([namespace string?, body v1beta1/ingress, ] (create-namespaced-ingress-with-http-info namespace body nil))
  ([namespace string?, body v1beta1/ingress, {:keys [pretty]} (s/map-of keyword? any?)]
   (check-required-params namespace body)
   (call-api "/apis/extensions/v1beta1/namespaces/{namespace}/ingresses" :post
             {:path-params   {"namespace" namespace }
              :header-params {}
              :query-params  {"pretty" pretty }
              :form-params   {}
              :body-param    body
              :content-types []
              :accepts       ["application/json" "application/yaml" "application/vnd.kubernetes.protobuf"]
              :auth-names    ["BearerToken"]})))

(defn-spec create-namespaced-ingress v1beta1/ingress-spec
  "
  create an Ingress"
  ([namespace string?, body v1beta1/ingress, ] (create-namespaced-ingress namespace body nil))
  ([namespace string?, body v1beta1/ingress, optional-params any?]
   (let [res (:data (create-namespaced-ingress-with-http-info namespace body optional-params))]
     (if (:decode-models *api-context*)
        (st/decode v1beta1/ingress-spec res st/string-transformer)
        res))))


(defn-spec create-namespaced-network-policy-with-http-info any?
  "
  create a NetworkPolicy"
  ([namespace string?, body v1beta1/network-policy, ] (create-namespaced-network-policy-with-http-info namespace body nil))
  ([namespace string?, body v1beta1/network-policy, {:keys [pretty]} (s/map-of keyword? any?)]
   (check-required-params namespace body)
   (call-api "/apis/extensions/v1beta1/namespaces/{namespace}/networkpolicies" :post
             {:path-params   {"namespace" namespace }
              :header-params {}
              :query-params  {"pretty" pretty }
              :form-params   {}
              :body-param    body
              :content-types []
              :accepts       ["application/json" "application/yaml" "application/vnd.kubernetes.protobuf"]
              :auth-names    ["BearerToken"]})))

(defn-spec create-namespaced-network-policy v1beta1/network-policy-spec
  "
  create a NetworkPolicy"
  ([namespace string?, body v1beta1/network-policy, ] (create-namespaced-network-policy namespace body nil))
  ([namespace string?, body v1beta1/network-policy, optional-params any?]
   (let [res (:data (create-namespaced-network-policy-with-http-info namespace body optional-params))]
     (if (:decode-models *api-context*)
        (st/decode v1beta1/network-policy-spec res st/string-transformer)
        res))))


(defn-spec create-namespaced-replica-set-with-http-info any?
  "
  create a ReplicaSet"
  ([namespace string?, body v1beta1/replica-set, ] (create-namespaced-replica-set-with-http-info namespace body nil))
  ([namespace string?, body v1beta1/replica-set, {:keys [pretty]} (s/map-of keyword? any?)]
   (check-required-params namespace body)
   (call-api "/apis/extensions/v1beta1/namespaces/{namespace}/replicasets" :post
             {:path-params   {"namespace" namespace }
              :header-params {}
              :query-params  {"pretty" pretty }
              :form-params   {}
              :body-param    body
              :content-types []
              :accepts       ["application/json" "application/yaml" "application/vnd.kubernetes.protobuf"]
              :auth-names    ["BearerToken"]})))

(defn-spec create-namespaced-replica-set v1beta1/replica-set-spec
  "
  create a ReplicaSet"
  ([namespace string?, body v1beta1/replica-set, ] (create-namespaced-replica-set namespace body nil))
  ([namespace string?, body v1beta1/replica-set, optional-params any?]
   (let [res (:data (create-namespaced-replica-set-with-http-info namespace body optional-params))]
     (if (:decode-models *api-context*)
        (st/decode v1beta1/replica-set-spec res st/string-transformer)
        res))))


(defn-spec create-pod-security-policy-with-http-info any?
  "
  create a PodSecurityPolicy"
  ([body v1beta1/pod-security-policy, ] (create-pod-security-policy-with-http-info body nil))
  ([body v1beta1/pod-security-policy, {:keys [pretty]} (s/map-of keyword? any?)]
   (check-required-params body)
   (call-api "/apis/extensions/v1beta1/podsecuritypolicies" :post
             {:path-params   {}
              :header-params {}
              :query-params  {"pretty" pretty }
              :form-params   {}
              :body-param    body
              :content-types []
              :accepts       ["application/json" "application/yaml" "application/vnd.kubernetes.protobuf"]
              :auth-names    ["BearerToken"]})))

(defn-spec create-pod-security-policy v1beta1/pod-security-policy-spec
  "
  create a PodSecurityPolicy"
  ([body v1beta1/pod-security-policy, ] (create-pod-security-policy body nil))
  ([body v1beta1/pod-security-policy, optional-params any?]
   (let [res (:data (create-pod-security-policy-with-http-info body optional-params))]
     (if (:decode-models *api-context*)
        (st/decode v1beta1/pod-security-policy-spec res st/string-transformer)
        res))))


(defn-spec delete-collection-namespaced-daemon-set-with-http-info any?
  "
  delete collection of DaemonSet"
  ([namespace string?, ] (delete-collection-namespaced-daemon-set-with-http-info namespace nil))
  ([namespace string?, {:keys [pretty continue fieldSelector includeUninitialized labelSelector limit resourceVersion timeoutSeconds watch]} (s/map-of keyword? any?)]
   (check-required-params namespace)
   (call-api "/apis/extensions/v1beta1/namespaces/{namespace}/daemonsets" :delete
             {:path-params   {"namespace" namespace }
              :header-params {}
              :query-params  {"pretty" pretty "continue" continue "fieldSelector" fieldSelector "includeUninitialized" includeUninitialized "labelSelector" labelSelector "limit" limit "resourceVersion" resourceVersion "timeoutSeconds" timeoutSeconds "watch" watch }
              :form-params   {}
              :content-types []
              :accepts       ["application/json" "application/yaml" "application/vnd.kubernetes.protobuf"]
              :auth-names    ["BearerToken"]})))

(defn-spec delete-collection-namespaced-daemon-set v1/status-spec
  "
  delete collection of DaemonSet"
  ([namespace string?, ] (delete-collection-namespaced-daemon-set namespace nil))
  ([namespace string?, optional-params any?]
   (let [res (:data (delete-collection-namespaced-daemon-set-with-http-info namespace optional-params))]
     (if (:decode-models *api-context*)
        (st/decode v1/status-spec res st/string-transformer)
        res))))


(defn-spec delete-collection-namespaced-deployment-with-http-info any?
  "
  delete collection of Deployment"
  ([namespace string?, ] (delete-collection-namespaced-deployment-with-http-info namespace nil))
  ([namespace string?, {:keys [pretty continue fieldSelector includeUninitialized labelSelector limit resourceVersion timeoutSeconds watch]} (s/map-of keyword? any?)]
   (check-required-params namespace)
   (call-api "/apis/extensions/v1beta1/namespaces/{namespace}/deployments" :delete
             {:path-params   {"namespace" namespace }
              :header-params {}
              :query-params  {"pretty" pretty "continue" continue "fieldSelector" fieldSelector "includeUninitialized" includeUninitialized "labelSelector" labelSelector "limit" limit "resourceVersion" resourceVersion "timeoutSeconds" timeoutSeconds "watch" watch }
              :form-params   {}
              :content-types []
              :accepts       ["application/json" "application/yaml" "application/vnd.kubernetes.protobuf"]
              :auth-names    ["BearerToken"]})))

(defn-spec delete-collection-namespaced-deployment v1/status-spec
  "
  delete collection of Deployment"
  ([namespace string?, ] (delete-collection-namespaced-deployment namespace nil))
  ([namespace string?, optional-params any?]
   (let [res (:data (delete-collection-namespaced-deployment-with-http-info namespace optional-params))]
     (if (:decode-models *api-context*)
        (st/decode v1/status-spec res st/string-transformer)
        res))))


(defn-spec delete-collection-namespaced-ingress-with-http-info any?
  "
  delete collection of Ingress"
  ([namespace string?, ] (delete-collection-namespaced-ingress-with-http-info namespace nil))
  ([namespace string?, {:keys [pretty continue fieldSelector includeUninitialized labelSelector limit resourceVersion timeoutSeconds watch]} (s/map-of keyword? any?)]
   (check-required-params namespace)
   (call-api "/apis/extensions/v1beta1/namespaces/{namespace}/ingresses" :delete
             {:path-params   {"namespace" namespace }
              :header-params {}
              :query-params  {"pretty" pretty "continue" continue "fieldSelector" fieldSelector "includeUninitialized" includeUninitialized "labelSelector" labelSelector "limit" limit "resourceVersion" resourceVersion "timeoutSeconds" timeoutSeconds "watch" watch }
              :form-params   {}
              :content-types []
              :accepts       ["application/json" "application/yaml" "application/vnd.kubernetes.protobuf"]
              :auth-names    ["BearerToken"]})))

(defn-spec delete-collection-namespaced-ingress v1/status-spec
  "
  delete collection of Ingress"
  ([namespace string?, ] (delete-collection-namespaced-ingress namespace nil))
  ([namespace string?, optional-params any?]
   (let [res (:data (delete-collection-namespaced-ingress-with-http-info namespace optional-params))]
     (if (:decode-models *api-context*)
        (st/decode v1/status-spec res st/string-transformer)
        res))))


(defn-spec delete-collection-namespaced-network-policy-with-http-info any?
  "
  delete collection of NetworkPolicy"
  ([namespace string?, ] (delete-collection-namespaced-network-policy-with-http-info namespace nil))
  ([namespace string?, {:keys [pretty continue fieldSelector includeUninitialized labelSelector limit resourceVersion timeoutSeconds watch]} (s/map-of keyword? any?)]
   (check-required-params namespace)
   (call-api "/apis/extensions/v1beta1/namespaces/{namespace}/networkpolicies" :delete
             {:path-params   {"namespace" namespace }
              :header-params {}
              :query-params  {"pretty" pretty "continue" continue "fieldSelector" fieldSelector "includeUninitialized" includeUninitialized "labelSelector" labelSelector "limit" limit "resourceVersion" resourceVersion "timeoutSeconds" timeoutSeconds "watch" watch }
              :form-params   {}
              :content-types []
              :accepts       ["application/json" "application/yaml" "application/vnd.kubernetes.protobuf"]
              :auth-names    ["BearerToken"]})))

(defn-spec delete-collection-namespaced-network-policy v1/status-spec
  "
  delete collection of NetworkPolicy"
  ([namespace string?, ] (delete-collection-namespaced-network-policy namespace nil))
  ([namespace string?, optional-params any?]
   (let [res (:data (delete-collection-namespaced-network-policy-with-http-info namespace optional-params))]
     (if (:decode-models *api-context*)
        (st/decode v1/status-spec res st/string-transformer)
        res))))


(defn-spec delete-collection-namespaced-replica-set-with-http-info any?
  "
  delete collection of ReplicaSet"
  ([namespace string?, ] (delete-collection-namespaced-replica-set-with-http-info namespace nil))
  ([namespace string?, {:keys [pretty continue fieldSelector includeUninitialized labelSelector limit resourceVersion timeoutSeconds watch]} (s/map-of keyword? any?)]
   (check-required-params namespace)
   (call-api "/apis/extensions/v1beta1/namespaces/{namespace}/replicasets" :delete
             {:path-params   {"namespace" namespace }
              :header-params {}
              :query-params  {"pretty" pretty "continue" continue "fieldSelector" fieldSelector "includeUninitialized" includeUninitialized "labelSelector" labelSelector "limit" limit "resourceVersion" resourceVersion "timeoutSeconds" timeoutSeconds "watch" watch }
              :form-params   {}
              :content-types []
              :accepts       ["application/json" "application/yaml" "application/vnd.kubernetes.protobuf"]
              :auth-names    ["BearerToken"]})))

(defn-spec delete-collection-namespaced-replica-set v1/status-spec
  "
  delete collection of ReplicaSet"
  ([namespace string?, ] (delete-collection-namespaced-replica-set namespace nil))
  ([namespace string?, optional-params any?]
   (let [res (:data (delete-collection-namespaced-replica-set-with-http-info namespace optional-params))]
     (if (:decode-models *api-context*)
        (st/decode v1/status-spec res st/string-transformer)
        res))))


(defn-spec delete-collection-pod-security-policy-with-http-info any?
  "
  delete collection of PodSecurityPolicy"
  ([] (delete-collection-pod-security-policy-with-http-info nil))
  ([{:keys [pretty continue fieldSelector includeUninitialized labelSelector limit resourceVersion timeoutSeconds watch]} (s/map-of keyword? any?)]
   (call-api "/apis/extensions/v1beta1/podsecuritypolicies" :delete
             {:path-params   {}
              :header-params {}
              :query-params  {"pretty" pretty "continue" continue "fieldSelector" fieldSelector "includeUninitialized" includeUninitialized "labelSelector" labelSelector "limit" limit "resourceVersion" resourceVersion "timeoutSeconds" timeoutSeconds "watch" watch }
              :form-params   {}
              :content-types []
              :accepts       ["application/json" "application/yaml" "application/vnd.kubernetes.protobuf"]
              :auth-names    ["BearerToken"]})))

(defn-spec delete-collection-pod-security-policy v1/status-spec
  "
  delete collection of PodSecurityPolicy"
  ([] (delete-collection-pod-security-policy nil))
  ([optional-params any?]
   (let [res (:data (delete-collection-pod-security-policy-with-http-info optional-params))]
     (if (:decode-models *api-context*)
        (st/decode v1/status-spec res st/string-transformer)
        res))))


(defn-spec delete-namespaced-daemon-set-with-http-info any?
  "
  delete a DaemonSet"
  ([name string?, namespace string?, body v1/delete-options, ] (delete-namespaced-daemon-set-with-http-info name namespace body nil))
  ([name string?, namespace string?, body v1/delete-options, {:keys [pretty gracePeriodSeconds orphanDependents propagationPolicy]} (s/map-of keyword? any?)]
   (check-required-params name namespace body)
   (call-api "/apis/extensions/v1beta1/namespaces/{namespace}/daemonsets/{name}" :delete
             {:path-params   {"name" name "namespace" namespace }
              :header-params {}
              :query-params  {"pretty" pretty "gracePeriodSeconds" gracePeriodSeconds "orphanDependents" orphanDependents "propagationPolicy" propagationPolicy }
              :form-params   {}
              :body-param    body
              :content-types []
              :accepts       ["application/json" "application/yaml" "application/vnd.kubernetes.protobuf"]
              :auth-names    ["BearerToken"]})))

(defn-spec delete-namespaced-daemon-set v1/status-spec
  "
  delete a DaemonSet"
  ([name string?, namespace string?, body v1/delete-options, ] (delete-namespaced-daemon-set name namespace body nil))
  ([name string?, namespace string?, body v1/delete-options, optional-params any?]
   (let [res (:data (delete-namespaced-daemon-set-with-http-info name namespace body optional-params))]
     (if (:decode-models *api-context*)
        (st/decode v1/status-spec res st/string-transformer)
        res))))


(defn-spec delete-namespaced-deployment-with-http-info any?
  "
  delete a Deployment"
  ([name string?, namespace string?, body v1/delete-options, ] (delete-namespaced-deployment-with-http-info name namespace body nil))
  ([name string?, namespace string?, body v1/delete-options, {:keys [pretty gracePeriodSeconds orphanDependents propagationPolicy]} (s/map-of keyword? any?)]
   (check-required-params name namespace body)
   (call-api "/apis/extensions/v1beta1/namespaces/{namespace}/deployments/{name}" :delete
             {:path-params   {"name" name "namespace" namespace }
              :header-params {}
              :query-params  {"pretty" pretty "gracePeriodSeconds" gracePeriodSeconds "orphanDependents" orphanDependents "propagationPolicy" propagationPolicy }
              :form-params   {}
              :body-param    body
              :content-types []
              :accepts       ["application/json" "application/yaml" "application/vnd.kubernetes.protobuf"]
              :auth-names    ["BearerToken"]})))

(defn-spec delete-namespaced-deployment v1/status-spec
  "
  delete a Deployment"
  ([name string?, namespace string?, body v1/delete-options, ] (delete-namespaced-deployment name namespace body nil))
  ([name string?, namespace string?, body v1/delete-options, optional-params any?]
   (let [res (:data (delete-namespaced-deployment-with-http-info name namespace body optional-params))]
     (if (:decode-models *api-context*)
        (st/decode v1/status-spec res st/string-transformer)
        res))))


(defn-spec delete-namespaced-ingress-with-http-info any?
  "
  delete an Ingress"
  ([name string?, namespace string?, body v1/delete-options, ] (delete-namespaced-ingress-with-http-info name namespace body nil))
  ([name string?, namespace string?, body v1/delete-options, {:keys [pretty gracePeriodSeconds orphanDependents propagationPolicy]} (s/map-of keyword? any?)]
   (check-required-params name namespace body)
   (call-api "/apis/extensions/v1beta1/namespaces/{namespace}/ingresses/{name}" :delete
             {:path-params   {"name" name "namespace" namespace }
              :header-params {}
              :query-params  {"pretty" pretty "gracePeriodSeconds" gracePeriodSeconds "orphanDependents" orphanDependents "propagationPolicy" propagationPolicy }
              :form-params   {}
              :body-param    body
              :content-types []
              :accepts       ["application/json" "application/yaml" "application/vnd.kubernetes.protobuf"]
              :auth-names    ["BearerToken"]})))

(defn-spec delete-namespaced-ingress v1/status-spec
  "
  delete an Ingress"
  ([name string?, namespace string?, body v1/delete-options, ] (delete-namespaced-ingress name namespace body nil))
  ([name string?, namespace string?, body v1/delete-options, optional-params any?]
   (let [res (:data (delete-namespaced-ingress-with-http-info name namespace body optional-params))]
     (if (:decode-models *api-context*)
        (st/decode v1/status-spec res st/string-transformer)
        res))))


(defn-spec delete-namespaced-network-policy-with-http-info any?
  "
  delete a NetworkPolicy"
  ([name string?, namespace string?, body v1/delete-options, ] (delete-namespaced-network-policy-with-http-info name namespace body nil))
  ([name string?, namespace string?, body v1/delete-options, {:keys [pretty gracePeriodSeconds orphanDependents propagationPolicy]} (s/map-of keyword? any?)]
   (check-required-params name namespace body)
   (call-api "/apis/extensions/v1beta1/namespaces/{namespace}/networkpolicies/{name}" :delete
             {:path-params   {"name" name "namespace" namespace }
              :header-params {}
              :query-params  {"pretty" pretty "gracePeriodSeconds" gracePeriodSeconds "orphanDependents" orphanDependents "propagationPolicy" propagationPolicy }
              :form-params   {}
              :body-param    body
              :content-types []
              :accepts       ["application/json" "application/yaml" "application/vnd.kubernetes.protobuf"]
              :auth-names    ["BearerToken"]})))

(defn-spec delete-namespaced-network-policy v1/status-spec
  "
  delete a NetworkPolicy"
  ([name string?, namespace string?, body v1/delete-options, ] (delete-namespaced-network-policy name namespace body nil))
  ([name string?, namespace string?, body v1/delete-options, optional-params any?]
   (let [res (:data (delete-namespaced-network-policy-with-http-info name namespace body optional-params))]
     (if (:decode-models *api-context*)
        (st/decode v1/status-spec res st/string-transformer)
        res))))


(defn-spec delete-namespaced-replica-set-with-http-info any?
  "
  delete a ReplicaSet"
  ([name string?, namespace string?, body v1/delete-options, ] (delete-namespaced-replica-set-with-http-info name namespace body nil))
  ([name string?, namespace string?, body v1/delete-options, {:keys [pretty gracePeriodSeconds orphanDependents propagationPolicy]} (s/map-of keyword? any?)]
   (check-required-params name namespace body)
   (call-api "/apis/extensions/v1beta1/namespaces/{namespace}/replicasets/{name}" :delete
             {:path-params   {"name" name "namespace" namespace }
              :header-params {}
              :query-params  {"pretty" pretty "gracePeriodSeconds" gracePeriodSeconds "orphanDependents" orphanDependents "propagationPolicy" propagationPolicy }
              :form-params   {}
              :body-param    body
              :content-types []
              :accepts       ["application/json" "application/yaml" "application/vnd.kubernetes.protobuf"]
              :auth-names    ["BearerToken"]})))

(defn-spec delete-namespaced-replica-set v1/status-spec
  "
  delete a ReplicaSet"
  ([name string?, namespace string?, body v1/delete-options, ] (delete-namespaced-replica-set name namespace body nil))
  ([name string?, namespace string?, body v1/delete-options, optional-params any?]
   (let [res (:data (delete-namespaced-replica-set-with-http-info name namespace body optional-params))]
     (if (:decode-models *api-context*)
        (st/decode v1/status-spec res st/string-transformer)
        res))))


(defn-spec delete-pod-security-policy-with-http-info any?
  "
  delete a PodSecurityPolicy"
  ([name string?, body v1/delete-options, ] (delete-pod-security-policy-with-http-info name body nil))
  ([name string?, body v1/delete-options, {:keys [pretty gracePeriodSeconds orphanDependents propagationPolicy]} (s/map-of keyword? any?)]
   (check-required-params name body)
   (call-api "/apis/extensions/v1beta1/podsecuritypolicies/{name}" :delete
             {:path-params   {"name" name }
              :header-params {}
              :query-params  {"pretty" pretty "gracePeriodSeconds" gracePeriodSeconds "orphanDependents" orphanDependents "propagationPolicy" propagationPolicy }
              :form-params   {}
              :body-param    body
              :content-types []
              :accepts       ["application/json" "application/yaml" "application/vnd.kubernetes.protobuf"]
              :auth-names    ["BearerToken"]})))

(defn-spec delete-pod-security-policy v1/status-spec
  "
  delete a PodSecurityPolicy"
  ([name string?, body v1/delete-options, ] (delete-pod-security-policy name body nil))
  ([name string?, body v1/delete-options, optional-params any?]
   (let [res (:data (delete-pod-security-policy-with-http-info name body optional-params))]
     (if (:decode-models *api-context*)
        (st/decode v1/status-spec res st/string-transformer)
        res))))


(defn-spec get-api-resources-with-http-info any?
  "
  get available resources"
  []
  (call-api "/apis/extensions/v1beta1/" :get
            {:path-params   {}
             :header-params {}
             :query-params  {}
             :form-params   {}
             :content-types []
             :accepts       ["application/json" "application/yaml" "application/vnd.kubernetes.protobuf"]
             :auth-names    ["BearerToken"]}))

(defn-spec get-api-resources v1/api-resource-list-spec
  "
  get available resources"
  []
  (let [res (:data (get-api-resources-with-http-info))]
    (if (:decode-models *api-context*)
       (st/decode v1/api-resource-list-spec res st/string-transformer)
       res)))


(defn-spec list-daemon-set-for-all-namespaces-with-http-info any?
  "
  list or watch objects of kind DaemonSet"
  ([] (list-daemon-set-for-all-namespaces-with-http-info nil))
  ([{:keys [continue fieldSelector includeUninitialized labelSelector limit pretty resourceVersion timeoutSeconds watch]} (s/map-of keyword? any?)]
   (call-api "/apis/extensions/v1beta1/daemonsets" :get
             {:path-params   {}
              :header-params {}
              :query-params  {"continue" continue "fieldSelector" fieldSelector "includeUninitialized" includeUninitialized "labelSelector" labelSelector "limit" limit "pretty" pretty "resourceVersion" resourceVersion "timeoutSeconds" timeoutSeconds "watch" watch }
              :form-params   {}
              :content-types []
              :accepts       ["application/json" "application/yaml" "application/vnd.kubernetes.protobuf" "application/json;stream=watch" "application/vnd.kubernetes.protobuf;stream=watch"]
              :auth-names    ["BearerToken"]})))

(defn-spec list-daemon-set-for-all-namespaces v1beta1/daemon-set-list-spec
  "
  list or watch objects of kind DaemonSet"
  ([] (list-daemon-set-for-all-namespaces nil))
  ([optional-params any?]
   (let [res (:data (list-daemon-set-for-all-namespaces-with-http-info optional-params))]
     (if (:decode-models *api-context*)
        (st/decode v1beta1/daemon-set-list-spec res st/string-transformer)
        res))))


(defn-spec list-deployment-for-all-namespaces-with-http-info any?
  "
  list or watch objects of kind Deployment"
  ([] (list-deployment-for-all-namespaces-with-http-info nil))
  ([{:keys [continue fieldSelector includeUninitialized labelSelector limit pretty resourceVersion timeoutSeconds watch]} (s/map-of keyword? any?)]
   (call-api "/apis/extensions/v1beta1/deployments" :get
             {:path-params   {}
              :header-params {}
              :query-params  {"continue" continue "fieldSelector" fieldSelector "includeUninitialized" includeUninitialized "labelSelector" labelSelector "limit" limit "pretty" pretty "resourceVersion" resourceVersion "timeoutSeconds" timeoutSeconds "watch" watch }
              :form-params   {}
              :content-types []
              :accepts       ["application/json" "application/yaml" "application/vnd.kubernetes.protobuf" "application/json;stream=watch" "application/vnd.kubernetes.protobuf;stream=watch"]
              :auth-names    ["BearerToken"]})))

(defn-spec list-deployment-for-all-namespaces extensions/v1beta1/deployment-list-spec
  "
  list or watch objects of kind Deployment"
  ([] (list-deployment-for-all-namespaces nil))
  ([optional-params any?]
   (let [res (:data (list-deployment-for-all-namespaces-with-http-info optional-params))]
     (if (:decode-models *api-context*)
        (st/decode extensions/v1beta1/deployment-list-spec res st/string-transformer)
        res))))


(defn-spec list-ingress-for-all-namespaces-with-http-info any?
  "
  list or watch objects of kind Ingress"
  ([] (list-ingress-for-all-namespaces-with-http-info nil))
  ([{:keys [continue fieldSelector includeUninitialized labelSelector limit pretty resourceVersion timeoutSeconds watch]} (s/map-of keyword? any?)]
   (call-api "/apis/extensions/v1beta1/ingresses" :get
             {:path-params   {}
              :header-params {}
              :query-params  {"continue" continue "fieldSelector" fieldSelector "includeUninitialized" includeUninitialized "labelSelector" labelSelector "limit" limit "pretty" pretty "resourceVersion" resourceVersion "timeoutSeconds" timeoutSeconds "watch" watch }
              :form-params   {}
              :content-types []
              :accepts       ["application/json" "application/yaml" "application/vnd.kubernetes.protobuf" "application/json;stream=watch" "application/vnd.kubernetes.protobuf;stream=watch"]
              :auth-names    ["BearerToken"]})))

(defn-spec list-ingress-for-all-namespaces v1beta1/ingress-list-spec
  "
  list or watch objects of kind Ingress"
  ([] (list-ingress-for-all-namespaces nil))
  ([optional-params any?]
   (let [res (:data (list-ingress-for-all-namespaces-with-http-info optional-params))]
     (if (:decode-models *api-context*)
        (st/decode v1beta1/ingress-list-spec res st/string-transformer)
        res))))


(defn-spec list-namespaced-daemon-set-with-http-info any?
  "
  list or watch objects of kind DaemonSet"
  ([namespace string?, ] (list-namespaced-daemon-set-with-http-info namespace nil))
  ([namespace string?, {:keys [pretty continue fieldSelector includeUninitialized labelSelector limit resourceVersion timeoutSeconds watch]} (s/map-of keyword? any?)]
   (check-required-params namespace)
   (call-api "/apis/extensions/v1beta1/namespaces/{namespace}/daemonsets" :get
             {:path-params   {"namespace" namespace }
              :header-params {}
              :query-params  {"pretty" pretty "continue" continue "fieldSelector" fieldSelector "includeUninitialized" includeUninitialized "labelSelector" labelSelector "limit" limit "resourceVersion" resourceVersion "timeoutSeconds" timeoutSeconds "watch" watch }
              :form-params   {}
              :content-types []
              :accepts       ["application/json" "application/yaml" "application/vnd.kubernetes.protobuf" "application/json;stream=watch" "application/vnd.kubernetes.protobuf;stream=watch"]
              :auth-names    ["BearerToken"]})))

(defn-spec list-namespaced-daemon-set v1beta1/daemon-set-list-spec
  "
  list or watch objects of kind DaemonSet"
  ([namespace string?, ] (list-namespaced-daemon-set namespace nil))
  ([namespace string?, optional-params any?]
   (let [res (:data (list-namespaced-daemon-set-with-http-info namespace optional-params))]
     (if (:decode-models *api-context*)
        (st/decode v1beta1/daemon-set-list-spec res st/string-transformer)
        res))))


(defn-spec list-namespaced-deployment-with-http-info any?
  "
  list or watch objects of kind Deployment"
  ([namespace string?, ] (list-namespaced-deployment-with-http-info namespace nil))
  ([namespace string?, {:keys [pretty continue fieldSelector includeUninitialized labelSelector limit resourceVersion timeoutSeconds watch]} (s/map-of keyword? any?)]
   (check-required-params namespace)
   (call-api "/apis/extensions/v1beta1/namespaces/{namespace}/deployments" :get
             {:path-params   {"namespace" namespace }
              :header-params {}
              :query-params  {"pretty" pretty "continue" continue "fieldSelector" fieldSelector "includeUninitialized" includeUninitialized "labelSelector" labelSelector "limit" limit "resourceVersion" resourceVersion "timeoutSeconds" timeoutSeconds "watch" watch }
              :form-params   {}
              :content-types []
              :accepts       ["application/json" "application/yaml" "application/vnd.kubernetes.protobuf" "application/json;stream=watch" "application/vnd.kubernetes.protobuf;stream=watch"]
              :auth-names    ["BearerToken"]})))

(defn-spec list-namespaced-deployment extensions/v1beta1/deployment-list-spec
  "
  list or watch objects of kind Deployment"
  ([namespace string?, ] (list-namespaced-deployment namespace nil))
  ([namespace string?, optional-params any?]
   (let [res (:data (list-namespaced-deployment-with-http-info namespace optional-params))]
     (if (:decode-models *api-context*)
        (st/decode extensions/v1beta1/deployment-list-spec res st/string-transformer)
        res))))


(defn-spec list-namespaced-ingress-with-http-info any?
  "
  list or watch objects of kind Ingress"
  ([namespace string?, ] (list-namespaced-ingress-with-http-info namespace nil))
  ([namespace string?, {:keys [pretty continue fieldSelector includeUninitialized labelSelector limit resourceVersion timeoutSeconds watch]} (s/map-of keyword? any?)]
   (check-required-params namespace)
   (call-api "/apis/extensions/v1beta1/namespaces/{namespace}/ingresses" :get
             {:path-params   {"namespace" namespace }
              :header-params {}
              :query-params  {"pretty" pretty "continue" continue "fieldSelector" fieldSelector "includeUninitialized" includeUninitialized "labelSelector" labelSelector "limit" limit "resourceVersion" resourceVersion "timeoutSeconds" timeoutSeconds "watch" watch }
              :form-params   {}
              :content-types []
              :accepts       ["application/json" "application/yaml" "application/vnd.kubernetes.protobuf" "application/json;stream=watch" "application/vnd.kubernetes.protobuf;stream=watch"]
              :auth-names    ["BearerToken"]})))

(defn-spec list-namespaced-ingress v1beta1/ingress-list-spec
  "
  list or watch objects of kind Ingress"
  ([namespace string?, ] (list-namespaced-ingress namespace nil))
  ([namespace string?, optional-params any?]
   (let [res (:data (list-namespaced-ingress-with-http-info namespace optional-params))]
     (if (:decode-models *api-context*)
        (st/decode v1beta1/ingress-list-spec res st/string-transformer)
        res))))


(defn-spec list-namespaced-network-policy-with-http-info any?
  "
  list or watch objects of kind NetworkPolicy"
  ([namespace string?, ] (list-namespaced-network-policy-with-http-info namespace nil))
  ([namespace string?, {:keys [pretty continue fieldSelector includeUninitialized labelSelector limit resourceVersion timeoutSeconds watch]} (s/map-of keyword? any?)]
   (check-required-params namespace)
   (call-api "/apis/extensions/v1beta1/namespaces/{namespace}/networkpolicies" :get
             {:path-params   {"namespace" namespace }
              :header-params {}
              :query-params  {"pretty" pretty "continue" continue "fieldSelector" fieldSelector "includeUninitialized" includeUninitialized "labelSelector" labelSelector "limit" limit "resourceVersion" resourceVersion "timeoutSeconds" timeoutSeconds "watch" watch }
              :form-params   {}
              :content-types []
              :accepts       ["application/json" "application/yaml" "application/vnd.kubernetes.protobuf" "application/json;stream=watch" "application/vnd.kubernetes.protobuf;stream=watch"]
              :auth-names    ["BearerToken"]})))

(defn-spec list-namespaced-network-policy v1beta1/network-policy-list-spec
  "
  list or watch objects of kind NetworkPolicy"
  ([namespace string?, ] (list-namespaced-network-policy namespace nil))
  ([namespace string?, optional-params any?]
   (let [res (:data (list-namespaced-network-policy-with-http-info namespace optional-params))]
     (if (:decode-models *api-context*)
        (st/decode v1beta1/network-policy-list-spec res st/string-transformer)
        res))))


(defn-spec list-namespaced-replica-set-with-http-info any?
  "
  list or watch objects of kind ReplicaSet"
  ([namespace string?, ] (list-namespaced-replica-set-with-http-info namespace nil))
  ([namespace string?, {:keys [pretty continue fieldSelector includeUninitialized labelSelector limit resourceVersion timeoutSeconds watch]} (s/map-of keyword? any?)]
   (check-required-params namespace)
   (call-api "/apis/extensions/v1beta1/namespaces/{namespace}/replicasets" :get
             {:path-params   {"namespace" namespace }
              :header-params {}
              :query-params  {"pretty" pretty "continue" continue "fieldSelector" fieldSelector "includeUninitialized" includeUninitialized "labelSelector" labelSelector "limit" limit "resourceVersion" resourceVersion "timeoutSeconds" timeoutSeconds "watch" watch }
              :form-params   {}
              :content-types []
              :accepts       ["application/json" "application/yaml" "application/vnd.kubernetes.protobuf" "application/json;stream=watch" "application/vnd.kubernetes.protobuf;stream=watch"]
              :auth-names    ["BearerToken"]})))

(defn-spec list-namespaced-replica-set v1beta1/replica-set-list-spec
  "
  list or watch objects of kind ReplicaSet"
  ([namespace string?, ] (list-namespaced-replica-set namespace nil))
  ([namespace string?, optional-params any?]
   (let [res (:data (list-namespaced-replica-set-with-http-info namespace optional-params))]
     (if (:decode-models *api-context*)
        (st/decode v1beta1/replica-set-list-spec res st/string-transformer)
        res))))


(defn-spec list-network-policy-for-all-namespaces-with-http-info any?
  "
  list or watch objects of kind NetworkPolicy"
  ([] (list-network-policy-for-all-namespaces-with-http-info nil))
  ([{:keys [continue fieldSelector includeUninitialized labelSelector limit pretty resourceVersion timeoutSeconds watch]} (s/map-of keyword? any?)]
   (call-api "/apis/extensions/v1beta1/networkpolicies" :get
             {:path-params   {}
              :header-params {}
              :query-params  {"continue" continue "fieldSelector" fieldSelector "includeUninitialized" includeUninitialized "labelSelector" labelSelector "limit" limit "pretty" pretty "resourceVersion" resourceVersion "timeoutSeconds" timeoutSeconds "watch" watch }
              :form-params   {}
              :content-types []
              :accepts       ["application/json" "application/yaml" "application/vnd.kubernetes.protobuf" "application/json;stream=watch" "application/vnd.kubernetes.protobuf;stream=watch"]
              :auth-names    ["BearerToken"]})))

(defn-spec list-network-policy-for-all-namespaces v1beta1/network-policy-list-spec
  "
  list or watch objects of kind NetworkPolicy"
  ([] (list-network-policy-for-all-namespaces nil))
  ([optional-params any?]
   (let [res (:data (list-network-policy-for-all-namespaces-with-http-info optional-params))]
     (if (:decode-models *api-context*)
        (st/decode v1beta1/network-policy-list-spec res st/string-transformer)
        res))))


(defn-spec list-pod-security-policy-with-http-info any?
  "
  list or watch objects of kind PodSecurityPolicy"
  ([] (list-pod-security-policy-with-http-info nil))
  ([{:keys [pretty continue fieldSelector includeUninitialized labelSelector limit resourceVersion timeoutSeconds watch]} (s/map-of keyword? any?)]
   (call-api "/apis/extensions/v1beta1/podsecuritypolicies" :get
             {:path-params   {}
              :header-params {}
              :query-params  {"pretty" pretty "continue" continue "fieldSelector" fieldSelector "includeUninitialized" includeUninitialized "labelSelector" labelSelector "limit" limit "resourceVersion" resourceVersion "timeoutSeconds" timeoutSeconds "watch" watch }
              :form-params   {}
              :content-types []
              :accepts       ["application/json" "application/yaml" "application/vnd.kubernetes.protobuf" "application/json;stream=watch" "application/vnd.kubernetes.protobuf;stream=watch"]
              :auth-names    ["BearerToken"]})))

(defn-spec list-pod-security-policy v1beta1/pod-security-policy-list-spec
  "
  list or watch objects of kind PodSecurityPolicy"
  ([] (list-pod-security-policy nil))
  ([optional-params any?]
   (let [res (:data (list-pod-security-policy-with-http-info optional-params))]
     (if (:decode-models *api-context*)
        (st/decode v1beta1/pod-security-policy-list-spec res st/string-transformer)
        res))))


(defn-spec list-replica-set-for-all-namespaces-with-http-info any?
  "
  list or watch objects of kind ReplicaSet"
  ([] (list-replica-set-for-all-namespaces-with-http-info nil))
  ([{:keys [continue fieldSelector includeUninitialized labelSelector limit pretty resourceVersion timeoutSeconds watch]} (s/map-of keyword? any?)]
   (call-api "/apis/extensions/v1beta1/replicasets" :get
             {:path-params   {}
              :header-params {}
              :query-params  {"continue" continue "fieldSelector" fieldSelector "includeUninitialized" includeUninitialized "labelSelector" labelSelector "limit" limit "pretty" pretty "resourceVersion" resourceVersion "timeoutSeconds" timeoutSeconds "watch" watch }
              :form-params   {}
              :content-types []
              :accepts       ["application/json" "application/yaml" "application/vnd.kubernetes.protobuf" "application/json;stream=watch" "application/vnd.kubernetes.protobuf;stream=watch"]
              :auth-names    ["BearerToken"]})))

(defn-spec list-replica-set-for-all-namespaces v1beta1/replica-set-list-spec
  "
  list or watch objects of kind ReplicaSet"
  ([] (list-replica-set-for-all-namespaces nil))
  ([optional-params any?]
   (let [res (:data (list-replica-set-for-all-namespaces-with-http-info optional-params))]
     (if (:decode-models *api-context*)
        (st/decode v1beta1/replica-set-list-spec res st/string-transformer)
        res))))


(defn-spec patch-namespaced-daemon-set-with-http-info any?
  "
  partially update the specified DaemonSet"
  ([name string?, namespace string?, body any?, ] (patch-namespaced-daemon-set-with-http-info name namespace body nil))
  ([name string?, namespace string?, body any?, {:keys [pretty]} (s/map-of keyword? any?)]
   (check-required-params name namespace body)
   (call-api "/apis/extensions/v1beta1/namespaces/{namespace}/daemonsets/{name}" :patch
             {:path-params   {"name" name "namespace" namespace }
              :header-params {}
              :query-params  {"pretty" pretty }
              :form-params   {}
              :body-param    body
              :content-types ["application/json-patch+json" "application/merge-patch+json" "application/strategic-merge-patch+json"]
              :accepts       ["application/json" "application/yaml" "application/vnd.kubernetes.protobuf"]
              :auth-names    ["BearerToken"]})))

(defn-spec patch-namespaced-daemon-set v1beta1/daemon-set-spec
  "
  partially update the specified DaemonSet"
  ([name string?, namespace string?, body any?, ] (patch-namespaced-daemon-set name namespace body nil))
  ([name string?, namespace string?, body any?, optional-params any?]
   (let [res (:data (patch-namespaced-daemon-set-with-http-info name namespace body optional-params))]
     (if (:decode-models *api-context*)
        (st/decode v1beta1/daemon-set-spec res st/string-transformer)
        res))))


(defn-spec patch-namespaced-daemon-set-status-with-http-info any?
  "
  partially update status of the specified DaemonSet"
  ([name string?, namespace string?, body any?, ] (patch-namespaced-daemon-set-status-with-http-info name namespace body nil))
  ([name string?, namespace string?, body any?, {:keys [pretty]} (s/map-of keyword? any?)]
   (check-required-params name namespace body)
   (call-api "/apis/extensions/v1beta1/namespaces/{namespace}/daemonsets/{name}/status" :patch
             {:path-params   {"name" name "namespace" namespace }
              :header-params {}
              :query-params  {"pretty" pretty }
              :form-params   {}
              :body-param    body
              :content-types ["application/json-patch+json" "application/merge-patch+json" "application/strategic-merge-patch+json"]
              :accepts       ["application/json" "application/yaml" "application/vnd.kubernetes.protobuf"]
              :auth-names    ["BearerToken"]})))

(defn-spec patch-namespaced-daemon-set-status v1beta1/daemon-set-spec
  "
  partially update status of the specified DaemonSet"
  ([name string?, namespace string?, body any?, ] (patch-namespaced-daemon-set-status name namespace body nil))
  ([name string?, namespace string?, body any?, optional-params any?]
   (let [res (:data (patch-namespaced-daemon-set-status-with-http-info name namespace body optional-params))]
     (if (:decode-models *api-context*)
        (st/decode v1beta1/daemon-set-spec res st/string-transformer)
        res))))


(defn-spec patch-namespaced-deployment-with-http-info any?
  "
  partially update the specified Deployment"
  ([name string?, namespace string?, body any?, ] (patch-namespaced-deployment-with-http-info name namespace body nil))
  ([name string?, namespace string?, body any?, {:keys [pretty]} (s/map-of keyword? any?)]
   (check-required-params name namespace body)
   (call-api "/apis/extensions/v1beta1/namespaces/{namespace}/deployments/{name}" :patch
             {:path-params   {"name" name "namespace" namespace }
              :header-params {}
              :query-params  {"pretty" pretty }
              :form-params   {}
              :body-param    body
              :content-types ["application/json-patch+json" "application/merge-patch+json" "application/strategic-merge-patch+json"]
              :accepts       ["application/json" "application/yaml" "application/vnd.kubernetes.protobuf"]
              :auth-names    ["BearerToken"]})))

(defn-spec patch-namespaced-deployment extensions/v1beta1/deployment-spec
  "
  partially update the specified Deployment"
  ([name string?, namespace string?, body any?, ] (patch-namespaced-deployment name namespace body nil))
  ([name string?, namespace string?, body any?, optional-params any?]
   (let [res (:data (patch-namespaced-deployment-with-http-info name namespace body optional-params))]
     (if (:decode-models *api-context*)
        (st/decode extensions/v1beta1/deployment-spec res st/string-transformer)
        res))))


(defn-spec patch-namespaced-deployment-scale-with-http-info any?
  "
  partially update scale of the specified Deployment"
  ([name string?, namespace string?, body any?, ] (patch-namespaced-deployment-scale-with-http-info name namespace body nil))
  ([name string?, namespace string?, body any?, {:keys [pretty]} (s/map-of keyword? any?)]
   (check-required-params name namespace body)
   (call-api "/apis/extensions/v1beta1/namespaces/{namespace}/deployments/{name}/scale" :patch
             {:path-params   {"name" name "namespace" namespace }
              :header-params {}
              :query-params  {"pretty" pretty }
              :form-params   {}
              :body-param    body
              :content-types ["application/json-patch+json" "application/merge-patch+json" "application/strategic-merge-patch+json"]
              :accepts       ["application/json" "application/yaml" "application/vnd.kubernetes.protobuf"]
              :auth-names    ["BearerToken"]})))

(defn-spec patch-namespaced-deployment-scale extensions/v1beta1/scale-spec
  "
  partially update scale of the specified Deployment"
  ([name string?, namespace string?, body any?, ] (patch-namespaced-deployment-scale name namespace body nil))
  ([name string?, namespace string?, body any?, optional-params any?]
   (let [res (:data (patch-namespaced-deployment-scale-with-http-info name namespace body optional-params))]
     (if (:decode-models *api-context*)
        (st/decode extensions/v1beta1/scale-spec res st/string-transformer)
        res))))


(defn-spec patch-namespaced-deployment-status-with-http-info any?
  "
  partially update status of the specified Deployment"
  ([name string?, namespace string?, body any?, ] (patch-namespaced-deployment-status-with-http-info name namespace body nil))
  ([name string?, namespace string?, body any?, {:keys [pretty]} (s/map-of keyword? any?)]
   (check-required-params name namespace body)
   (call-api "/apis/extensions/v1beta1/namespaces/{namespace}/deployments/{name}/status" :patch
             {:path-params   {"name" name "namespace" namespace }
              :header-params {}
              :query-params  {"pretty" pretty }
              :form-params   {}
              :body-param    body
              :content-types ["application/json-patch+json" "application/merge-patch+json" "application/strategic-merge-patch+json"]
              :accepts       ["application/json" "application/yaml" "application/vnd.kubernetes.protobuf"]
              :auth-names    ["BearerToken"]})))

(defn-spec patch-namespaced-deployment-status extensions/v1beta1/deployment-spec
  "
  partially update status of the specified Deployment"
  ([name string?, namespace string?, body any?, ] (patch-namespaced-deployment-status name namespace body nil))
  ([name string?, namespace string?, body any?, optional-params any?]
   (let [res (:data (patch-namespaced-deployment-status-with-http-info name namespace body optional-params))]
     (if (:decode-models *api-context*)
        (st/decode extensions/v1beta1/deployment-spec res st/string-transformer)
        res))))


(defn-spec patch-namespaced-ingress-with-http-info any?
  "
  partially update the specified Ingress"
  ([name string?, namespace string?, body any?, ] (patch-namespaced-ingress-with-http-info name namespace body nil))
  ([name string?, namespace string?, body any?, {:keys [pretty]} (s/map-of keyword? any?)]
   (check-required-params name namespace body)
   (call-api "/apis/extensions/v1beta1/namespaces/{namespace}/ingresses/{name}" :patch
             {:path-params   {"name" name "namespace" namespace }
              :header-params {}
              :query-params  {"pretty" pretty }
              :form-params   {}
              :body-param    body
              :content-types ["application/json-patch+json" "application/merge-patch+json" "application/strategic-merge-patch+json"]
              :accepts       ["application/json" "application/yaml" "application/vnd.kubernetes.protobuf"]
              :auth-names    ["BearerToken"]})))

(defn-spec patch-namespaced-ingress v1beta1/ingress-spec
  "
  partially update the specified Ingress"
  ([name string?, namespace string?, body any?, ] (patch-namespaced-ingress name namespace body nil))
  ([name string?, namespace string?, body any?, optional-params any?]
   (let [res (:data (patch-namespaced-ingress-with-http-info name namespace body optional-params))]
     (if (:decode-models *api-context*)
        (st/decode v1beta1/ingress-spec res st/string-transformer)
        res))))


(defn-spec patch-namespaced-ingress-status-with-http-info any?
  "
  partially update status of the specified Ingress"
  ([name string?, namespace string?, body any?, ] (patch-namespaced-ingress-status-with-http-info name namespace body nil))
  ([name string?, namespace string?, body any?, {:keys [pretty]} (s/map-of keyword? any?)]
   (check-required-params name namespace body)
   (call-api "/apis/extensions/v1beta1/namespaces/{namespace}/ingresses/{name}/status" :patch
             {:path-params   {"name" name "namespace" namespace }
              :header-params {}
              :query-params  {"pretty" pretty }
              :form-params   {}
              :body-param    body
              :content-types ["application/json-patch+json" "application/merge-patch+json" "application/strategic-merge-patch+json"]
              :accepts       ["application/json" "application/yaml" "application/vnd.kubernetes.protobuf"]
              :auth-names    ["BearerToken"]})))

(defn-spec patch-namespaced-ingress-status v1beta1/ingress-spec
  "
  partially update status of the specified Ingress"
  ([name string?, namespace string?, body any?, ] (patch-namespaced-ingress-status name namespace body nil))
  ([name string?, namespace string?, body any?, optional-params any?]
   (let [res (:data (patch-namespaced-ingress-status-with-http-info name namespace body optional-params))]
     (if (:decode-models *api-context*)
        (st/decode v1beta1/ingress-spec res st/string-transformer)
        res))))


(defn-spec patch-namespaced-network-policy-with-http-info any?
  "
  partially update the specified NetworkPolicy"
  ([name string?, namespace string?, body any?, ] (patch-namespaced-network-policy-with-http-info name namespace body nil))
  ([name string?, namespace string?, body any?, {:keys [pretty]} (s/map-of keyword? any?)]
   (check-required-params name namespace body)
   (call-api "/apis/extensions/v1beta1/namespaces/{namespace}/networkpolicies/{name}" :patch
             {:path-params   {"name" name "namespace" namespace }
              :header-params {}
              :query-params  {"pretty" pretty }
              :form-params   {}
              :body-param    body
              :content-types ["application/json-patch+json" "application/merge-patch+json" "application/strategic-merge-patch+json"]
              :accepts       ["application/json" "application/yaml" "application/vnd.kubernetes.protobuf"]
              :auth-names    ["BearerToken"]})))

(defn-spec patch-namespaced-network-policy v1beta1/network-policy-spec
  "
  partially update the specified NetworkPolicy"
  ([name string?, namespace string?, body any?, ] (patch-namespaced-network-policy name namespace body nil))
  ([name string?, namespace string?, body any?, optional-params any?]
   (let [res (:data (patch-namespaced-network-policy-with-http-info name namespace body optional-params))]
     (if (:decode-models *api-context*)
        (st/decode v1beta1/network-policy-spec res st/string-transformer)
        res))))


(defn-spec patch-namespaced-replica-set-with-http-info any?
  "
  partially update the specified ReplicaSet"
  ([name string?, namespace string?, body any?, ] (patch-namespaced-replica-set-with-http-info name namespace body nil))
  ([name string?, namespace string?, body any?, {:keys [pretty]} (s/map-of keyword? any?)]
   (check-required-params name namespace body)
   (call-api "/apis/extensions/v1beta1/namespaces/{namespace}/replicasets/{name}" :patch
             {:path-params   {"name" name "namespace" namespace }
              :header-params {}
              :query-params  {"pretty" pretty }
              :form-params   {}
              :body-param    body
              :content-types ["application/json-patch+json" "application/merge-patch+json" "application/strategic-merge-patch+json"]
              :accepts       ["application/json" "application/yaml" "application/vnd.kubernetes.protobuf"]
              :auth-names    ["BearerToken"]})))

(defn-spec patch-namespaced-replica-set v1beta1/replica-set-spec
  "
  partially update the specified ReplicaSet"
  ([name string?, namespace string?, body any?, ] (patch-namespaced-replica-set name namespace body nil))
  ([name string?, namespace string?, body any?, optional-params any?]
   (let [res (:data (patch-namespaced-replica-set-with-http-info name namespace body optional-params))]
     (if (:decode-models *api-context*)
        (st/decode v1beta1/replica-set-spec res st/string-transformer)
        res))))


(defn-spec patch-namespaced-replica-set-scale-with-http-info any?
  "
  partially update scale of the specified ReplicaSet"
  ([name string?, namespace string?, body any?, ] (patch-namespaced-replica-set-scale-with-http-info name namespace body nil))
  ([name string?, namespace string?, body any?, {:keys [pretty]} (s/map-of keyword? any?)]
   (check-required-params name namespace body)
   (call-api "/apis/extensions/v1beta1/namespaces/{namespace}/replicasets/{name}/scale" :patch
             {:path-params   {"name" name "namespace" namespace }
              :header-params {}
              :query-params  {"pretty" pretty }
              :form-params   {}
              :body-param    body
              :content-types ["application/json-patch+json" "application/merge-patch+json" "application/strategic-merge-patch+json"]
              :accepts       ["application/json" "application/yaml" "application/vnd.kubernetes.protobuf"]
              :auth-names    ["BearerToken"]})))

(defn-spec patch-namespaced-replica-set-scale extensions/v1beta1/scale-spec
  "
  partially update scale of the specified ReplicaSet"
  ([name string?, namespace string?, body any?, ] (patch-namespaced-replica-set-scale name namespace body nil))
  ([name string?, namespace string?, body any?, optional-params any?]
   (let [res (:data (patch-namespaced-replica-set-scale-with-http-info name namespace body optional-params))]
     (if (:decode-models *api-context*)
        (st/decode extensions/v1beta1/scale-spec res st/string-transformer)
        res))))


(defn-spec patch-namespaced-replica-set-status-with-http-info any?
  "
  partially update status of the specified ReplicaSet"
  ([name string?, namespace string?, body any?, ] (patch-namespaced-replica-set-status-with-http-info name namespace body nil))
  ([name string?, namespace string?, body any?, {:keys [pretty]} (s/map-of keyword? any?)]
   (check-required-params name namespace body)
   (call-api "/apis/extensions/v1beta1/namespaces/{namespace}/replicasets/{name}/status" :patch
             {:path-params   {"name" name "namespace" namespace }
              :header-params {}
              :query-params  {"pretty" pretty }
              :form-params   {}
              :body-param    body
              :content-types ["application/json-patch+json" "application/merge-patch+json" "application/strategic-merge-patch+json"]
              :accepts       ["application/json" "application/yaml" "application/vnd.kubernetes.protobuf"]
              :auth-names    ["BearerToken"]})))

(defn-spec patch-namespaced-replica-set-status v1beta1/replica-set-spec
  "
  partially update status of the specified ReplicaSet"
  ([name string?, namespace string?, body any?, ] (patch-namespaced-replica-set-status name namespace body nil))
  ([name string?, namespace string?, body any?, optional-params any?]
   (let [res (:data (patch-namespaced-replica-set-status-with-http-info name namespace body optional-params))]
     (if (:decode-models *api-context*)
        (st/decode v1beta1/replica-set-spec res st/string-transformer)
        res))))


(defn-spec patch-namespaced-replication-controller-dummy-scale-with-http-info any?
  "
  partially update scale of the specified ReplicationControllerDummy"
  ([name string?, namespace string?, body any?, ] (patch-namespaced-replication-controller-dummy-scale-with-http-info name namespace body nil))
  ([name string?, namespace string?, body any?, {:keys [pretty]} (s/map-of keyword? any?)]
   (check-required-params name namespace body)
   (call-api "/apis/extensions/v1beta1/namespaces/{namespace}/replicationcontrollers/{name}/scale" :patch
             {:path-params   {"name" name "namespace" namespace }
              :header-params {}
              :query-params  {"pretty" pretty }
              :form-params   {}
              :body-param    body
              :content-types ["application/json-patch+json" "application/merge-patch+json" "application/strategic-merge-patch+json"]
              :accepts       ["application/json" "application/yaml" "application/vnd.kubernetes.protobuf"]
              :auth-names    ["BearerToken"]})))

(defn-spec patch-namespaced-replication-controller-dummy-scale extensions/v1beta1/scale-spec
  "
  partially update scale of the specified ReplicationControllerDummy"
  ([name string?, namespace string?, body any?, ] (patch-namespaced-replication-controller-dummy-scale name namespace body nil))
  ([name string?, namespace string?, body any?, optional-params any?]
   (let [res (:data (patch-namespaced-replication-controller-dummy-scale-with-http-info name namespace body optional-params))]
     (if (:decode-models *api-context*)
        (st/decode extensions/v1beta1/scale-spec res st/string-transformer)
        res))))


(defn-spec patch-pod-security-policy-with-http-info any?
  "
  partially update the specified PodSecurityPolicy"
  ([name string?, body any?, ] (patch-pod-security-policy-with-http-info name body nil))
  ([name string?, body any?, {:keys [pretty]} (s/map-of keyword? any?)]
   (check-required-params name body)
   (call-api "/apis/extensions/v1beta1/podsecuritypolicies/{name}" :patch
             {:path-params   {"name" name }
              :header-params {}
              :query-params  {"pretty" pretty }
              :form-params   {}
              :body-param    body
              :content-types ["application/json-patch+json" "application/merge-patch+json" "application/strategic-merge-patch+json"]
              :accepts       ["application/json" "application/yaml" "application/vnd.kubernetes.protobuf"]
              :auth-names    ["BearerToken"]})))

(defn-spec patch-pod-security-policy v1beta1/pod-security-policy-spec
  "
  partially update the specified PodSecurityPolicy"
  ([name string?, body any?, ] (patch-pod-security-policy name body nil))
  ([name string?, body any?, optional-params any?]
   (let [res (:data (patch-pod-security-policy-with-http-info name body optional-params))]
     (if (:decode-models *api-context*)
        (st/decode v1beta1/pod-security-policy-spec res st/string-transformer)
        res))))


(defn-spec read-namespaced-daemon-set-with-http-info any?
  "
  read the specified DaemonSet"
  ([name string?, namespace string?, ] (read-namespaced-daemon-set-with-http-info name namespace nil))
  ([name string?, namespace string?, {:keys [pretty exact export]} (s/map-of keyword? any?)]
   (check-required-params name namespace)
   (call-api "/apis/extensions/v1beta1/namespaces/{namespace}/daemonsets/{name}" :get
             {:path-params   {"name" name "namespace" namespace }
              :header-params {}
              :query-params  {"pretty" pretty "exact" exact "export" export }
              :form-params   {}
              :content-types []
              :accepts       ["application/json" "application/yaml" "application/vnd.kubernetes.protobuf"]
              :auth-names    ["BearerToken"]})))

(defn-spec read-namespaced-daemon-set v1beta1/daemon-set-spec
  "
  read the specified DaemonSet"
  ([name string?, namespace string?, ] (read-namespaced-daemon-set name namespace nil))
  ([name string?, namespace string?, optional-params any?]
   (let [res (:data (read-namespaced-daemon-set-with-http-info name namespace optional-params))]
     (if (:decode-models *api-context*)
        (st/decode v1beta1/daemon-set-spec res st/string-transformer)
        res))))


(defn-spec read-namespaced-daemon-set-status-with-http-info any?
  "
  read status of the specified DaemonSet"
  ([name string?, namespace string?, ] (read-namespaced-daemon-set-status-with-http-info name namespace nil))
  ([name string?, namespace string?, {:keys [pretty]} (s/map-of keyword? any?)]
   (check-required-params name namespace)
   (call-api "/apis/extensions/v1beta1/namespaces/{namespace}/daemonsets/{name}/status" :get
             {:path-params   {"name" name "namespace" namespace }
              :header-params {}
              :query-params  {"pretty" pretty }
              :form-params   {}
              :content-types []
              :accepts       ["application/json" "application/yaml" "application/vnd.kubernetes.protobuf"]
              :auth-names    ["BearerToken"]})))

(defn-spec read-namespaced-daemon-set-status v1beta1/daemon-set-spec
  "
  read status of the specified DaemonSet"
  ([name string?, namespace string?, ] (read-namespaced-daemon-set-status name namespace nil))
  ([name string?, namespace string?, optional-params any?]
   (let [res (:data (read-namespaced-daemon-set-status-with-http-info name namespace optional-params))]
     (if (:decode-models *api-context*)
        (st/decode v1beta1/daemon-set-spec res st/string-transformer)
        res))))


(defn-spec read-namespaced-deployment-with-http-info any?
  "
  read the specified Deployment"
  ([name string?, namespace string?, ] (read-namespaced-deployment-with-http-info name namespace nil))
  ([name string?, namespace string?, {:keys [pretty exact export]} (s/map-of keyword? any?)]
   (check-required-params name namespace)
   (call-api "/apis/extensions/v1beta1/namespaces/{namespace}/deployments/{name}" :get
             {:path-params   {"name" name "namespace" namespace }
              :header-params {}
              :query-params  {"pretty" pretty "exact" exact "export" export }
              :form-params   {}
              :content-types []
              :accepts       ["application/json" "application/yaml" "application/vnd.kubernetes.protobuf"]
              :auth-names    ["BearerToken"]})))

(defn-spec read-namespaced-deployment extensions/v1beta1/deployment-spec
  "
  read the specified Deployment"
  ([name string?, namespace string?, ] (read-namespaced-deployment name namespace nil))
  ([name string?, namespace string?, optional-params any?]
   (let [res (:data (read-namespaced-deployment-with-http-info name namespace optional-params))]
     (if (:decode-models *api-context*)
        (st/decode extensions/v1beta1/deployment-spec res st/string-transformer)
        res))))


(defn-spec read-namespaced-deployment-scale-with-http-info any?
  "
  read scale of the specified Deployment"
  ([name string?, namespace string?, ] (read-namespaced-deployment-scale-with-http-info name namespace nil))
  ([name string?, namespace string?, {:keys [pretty]} (s/map-of keyword? any?)]
   (check-required-params name namespace)
   (call-api "/apis/extensions/v1beta1/namespaces/{namespace}/deployments/{name}/scale" :get
             {:path-params   {"name" name "namespace" namespace }
              :header-params {}
              :query-params  {"pretty" pretty }
              :form-params   {}
              :content-types []
              :accepts       ["application/json" "application/yaml" "application/vnd.kubernetes.protobuf"]
              :auth-names    ["BearerToken"]})))

(defn-spec read-namespaced-deployment-scale extensions/v1beta1/scale-spec
  "
  read scale of the specified Deployment"
  ([name string?, namespace string?, ] (read-namespaced-deployment-scale name namespace nil))
  ([name string?, namespace string?, optional-params any?]
   (let [res (:data (read-namespaced-deployment-scale-with-http-info name namespace optional-params))]
     (if (:decode-models *api-context*)
        (st/decode extensions/v1beta1/scale-spec res st/string-transformer)
        res))))


(defn-spec read-namespaced-deployment-status-with-http-info any?
  "
  read status of the specified Deployment"
  ([name string?, namespace string?, ] (read-namespaced-deployment-status-with-http-info name namespace nil))
  ([name string?, namespace string?, {:keys [pretty]} (s/map-of keyword? any?)]
   (check-required-params name namespace)
   (call-api "/apis/extensions/v1beta1/namespaces/{namespace}/deployments/{name}/status" :get
             {:path-params   {"name" name "namespace" namespace }
              :header-params {}
              :query-params  {"pretty" pretty }
              :form-params   {}
              :content-types []
              :accepts       ["application/json" "application/yaml" "application/vnd.kubernetes.protobuf"]
              :auth-names    ["BearerToken"]})))

(defn-spec read-namespaced-deployment-status extensions/v1beta1/deployment-spec
  "
  read status of the specified Deployment"
  ([name string?, namespace string?, ] (read-namespaced-deployment-status name namespace nil))
  ([name string?, namespace string?, optional-params any?]
   (let [res (:data (read-namespaced-deployment-status-with-http-info name namespace optional-params))]
     (if (:decode-models *api-context*)
        (st/decode extensions/v1beta1/deployment-spec res st/string-transformer)
        res))))


(defn-spec read-namespaced-ingress-with-http-info any?
  "
  read the specified Ingress"
  ([name string?, namespace string?, ] (read-namespaced-ingress-with-http-info name namespace nil))
  ([name string?, namespace string?, {:keys [pretty exact export]} (s/map-of keyword? any?)]
   (check-required-params name namespace)
   (call-api "/apis/extensions/v1beta1/namespaces/{namespace}/ingresses/{name}" :get
             {:path-params   {"name" name "namespace" namespace }
              :header-params {}
              :query-params  {"pretty" pretty "exact" exact "export" export }
              :form-params   {}
              :content-types []
              :accepts       ["application/json" "application/yaml" "application/vnd.kubernetes.protobuf"]
              :auth-names    ["BearerToken"]})))

(defn-spec read-namespaced-ingress v1beta1/ingress-spec
  "
  read the specified Ingress"
  ([name string?, namespace string?, ] (read-namespaced-ingress name namespace nil))
  ([name string?, namespace string?, optional-params any?]
   (let [res (:data (read-namespaced-ingress-with-http-info name namespace optional-params))]
     (if (:decode-models *api-context*)
        (st/decode v1beta1/ingress-spec res st/string-transformer)
        res))))


(defn-spec read-namespaced-ingress-status-with-http-info any?
  "
  read status of the specified Ingress"
  ([name string?, namespace string?, ] (read-namespaced-ingress-status-with-http-info name namespace nil))
  ([name string?, namespace string?, {:keys [pretty]} (s/map-of keyword? any?)]
   (check-required-params name namespace)
   (call-api "/apis/extensions/v1beta1/namespaces/{namespace}/ingresses/{name}/status" :get
             {:path-params   {"name" name "namespace" namespace }
              :header-params {}
              :query-params  {"pretty" pretty }
              :form-params   {}
              :content-types []
              :accepts       ["application/json" "application/yaml" "application/vnd.kubernetes.protobuf"]
              :auth-names    ["BearerToken"]})))

(defn-spec read-namespaced-ingress-status v1beta1/ingress-spec
  "
  read status of the specified Ingress"
  ([name string?, namespace string?, ] (read-namespaced-ingress-status name namespace nil))
  ([name string?, namespace string?, optional-params any?]
   (let [res (:data (read-namespaced-ingress-status-with-http-info name namespace optional-params))]
     (if (:decode-models *api-context*)
        (st/decode v1beta1/ingress-spec res st/string-transformer)
        res))))


(defn-spec read-namespaced-network-policy-with-http-info any?
  "
  read the specified NetworkPolicy"
  ([name string?, namespace string?, ] (read-namespaced-network-policy-with-http-info name namespace nil))
  ([name string?, namespace string?, {:keys [pretty exact export]} (s/map-of keyword? any?)]
   (check-required-params name namespace)
   (call-api "/apis/extensions/v1beta1/namespaces/{namespace}/networkpolicies/{name}" :get
             {:path-params   {"name" name "namespace" namespace }
              :header-params {}
              :query-params  {"pretty" pretty "exact" exact "export" export }
              :form-params   {}
              :content-types []
              :accepts       ["application/json" "application/yaml" "application/vnd.kubernetes.protobuf"]
              :auth-names    ["BearerToken"]})))

(defn-spec read-namespaced-network-policy v1beta1/network-policy-spec
  "
  read the specified NetworkPolicy"
  ([name string?, namespace string?, ] (read-namespaced-network-policy name namespace nil))
  ([name string?, namespace string?, optional-params any?]
   (let [res (:data (read-namespaced-network-policy-with-http-info name namespace optional-params))]
     (if (:decode-models *api-context*)
        (st/decode v1beta1/network-policy-spec res st/string-transformer)
        res))))


(defn-spec read-namespaced-replica-set-with-http-info any?
  "
  read the specified ReplicaSet"
  ([name string?, namespace string?, ] (read-namespaced-replica-set-with-http-info name namespace nil))
  ([name string?, namespace string?, {:keys [pretty exact export]} (s/map-of keyword? any?)]
   (check-required-params name namespace)
   (call-api "/apis/extensions/v1beta1/namespaces/{namespace}/replicasets/{name}" :get
             {:path-params   {"name" name "namespace" namespace }
              :header-params {}
              :query-params  {"pretty" pretty "exact" exact "export" export }
              :form-params   {}
              :content-types []
              :accepts       ["application/json" "application/yaml" "application/vnd.kubernetes.protobuf"]
              :auth-names    ["BearerToken"]})))

(defn-spec read-namespaced-replica-set v1beta1/replica-set-spec
  "
  read the specified ReplicaSet"
  ([name string?, namespace string?, ] (read-namespaced-replica-set name namespace nil))
  ([name string?, namespace string?, optional-params any?]
   (let [res (:data (read-namespaced-replica-set-with-http-info name namespace optional-params))]
     (if (:decode-models *api-context*)
        (st/decode v1beta1/replica-set-spec res st/string-transformer)
        res))))


(defn-spec read-namespaced-replica-set-scale-with-http-info any?
  "
  read scale of the specified ReplicaSet"
  ([name string?, namespace string?, ] (read-namespaced-replica-set-scale-with-http-info name namespace nil))
  ([name string?, namespace string?, {:keys [pretty]} (s/map-of keyword? any?)]
   (check-required-params name namespace)
   (call-api "/apis/extensions/v1beta1/namespaces/{namespace}/replicasets/{name}/scale" :get
             {:path-params   {"name" name "namespace" namespace }
              :header-params {}
              :query-params  {"pretty" pretty }
              :form-params   {}
              :content-types []
              :accepts       ["application/json" "application/yaml" "application/vnd.kubernetes.protobuf"]
              :auth-names    ["BearerToken"]})))

(defn-spec read-namespaced-replica-set-scale extensions/v1beta1/scale-spec
  "
  read scale of the specified ReplicaSet"
  ([name string?, namespace string?, ] (read-namespaced-replica-set-scale name namespace nil))
  ([name string?, namespace string?, optional-params any?]
   (let [res (:data (read-namespaced-replica-set-scale-with-http-info name namespace optional-params))]
     (if (:decode-models *api-context*)
        (st/decode extensions/v1beta1/scale-spec res st/string-transformer)
        res))))


(defn-spec read-namespaced-replica-set-status-with-http-info any?
  "
  read status of the specified ReplicaSet"
  ([name string?, namespace string?, ] (read-namespaced-replica-set-status-with-http-info name namespace nil))
  ([name string?, namespace string?, {:keys [pretty]} (s/map-of keyword? any?)]
   (check-required-params name namespace)
   (call-api "/apis/extensions/v1beta1/namespaces/{namespace}/replicasets/{name}/status" :get
             {:path-params   {"name" name "namespace" namespace }
              :header-params {}
              :query-params  {"pretty" pretty }
              :form-params   {}
              :content-types []
              :accepts       ["application/json" "application/yaml" "application/vnd.kubernetes.protobuf"]
              :auth-names    ["BearerToken"]})))

(defn-spec read-namespaced-replica-set-status v1beta1/replica-set-spec
  "
  read status of the specified ReplicaSet"
  ([name string?, namespace string?, ] (read-namespaced-replica-set-status name namespace nil))
  ([name string?, namespace string?, optional-params any?]
   (let [res (:data (read-namespaced-replica-set-status-with-http-info name namespace optional-params))]
     (if (:decode-models *api-context*)
        (st/decode v1beta1/replica-set-spec res st/string-transformer)
        res))))


(defn-spec read-namespaced-replication-controller-dummy-scale-with-http-info any?
  "
  read scale of the specified ReplicationControllerDummy"
  ([name string?, namespace string?, ] (read-namespaced-replication-controller-dummy-scale-with-http-info name namespace nil))
  ([name string?, namespace string?, {:keys [pretty]} (s/map-of keyword? any?)]
   (check-required-params name namespace)
   (call-api "/apis/extensions/v1beta1/namespaces/{namespace}/replicationcontrollers/{name}/scale" :get
             {:path-params   {"name" name "namespace" namespace }
              :header-params {}
              :query-params  {"pretty" pretty }
              :form-params   {}
              :content-types []
              :accepts       ["application/json" "application/yaml" "application/vnd.kubernetes.protobuf"]
              :auth-names    ["BearerToken"]})))

(defn-spec read-namespaced-replication-controller-dummy-scale extensions/v1beta1/scale-spec
  "
  read scale of the specified ReplicationControllerDummy"
  ([name string?, namespace string?, ] (read-namespaced-replication-controller-dummy-scale name namespace nil))
  ([name string?, namespace string?, optional-params any?]
   (let [res (:data (read-namespaced-replication-controller-dummy-scale-with-http-info name namespace optional-params))]
     (if (:decode-models *api-context*)
        (st/decode extensions/v1beta1/scale-spec res st/string-transformer)
        res))))


(defn-spec read-pod-security-policy-with-http-info any?
  "
  read the specified PodSecurityPolicy"
  ([name string?, ] (read-pod-security-policy-with-http-info name nil))
  ([name string?, {:keys [pretty exact export]} (s/map-of keyword? any?)]
   (check-required-params name)
   (call-api "/apis/extensions/v1beta1/podsecuritypolicies/{name}" :get
             {:path-params   {"name" name }
              :header-params {}
              :query-params  {"pretty" pretty "exact" exact "export" export }
              :form-params   {}
              :content-types []
              :accepts       ["application/json" "application/yaml" "application/vnd.kubernetes.protobuf"]
              :auth-names    ["BearerToken"]})))

(defn-spec read-pod-security-policy v1beta1/pod-security-policy-spec
  "
  read the specified PodSecurityPolicy"
  ([name string?, ] (read-pod-security-policy name nil))
  ([name string?, optional-params any?]
   (let [res (:data (read-pod-security-policy-with-http-info name optional-params))]
     (if (:decode-models *api-context*)
        (st/decode v1beta1/pod-security-policy-spec res st/string-transformer)
        res))))


(defn-spec replace-namespaced-daemon-set-with-http-info any?
  "
  replace the specified DaemonSet"
  ([name string?, namespace string?, body v1beta1/daemon-set, ] (replace-namespaced-daemon-set-with-http-info name namespace body nil))
  ([name string?, namespace string?, body v1beta1/daemon-set, {:keys [pretty]} (s/map-of keyword? any?)]
   (check-required-params name namespace body)
   (call-api "/apis/extensions/v1beta1/namespaces/{namespace}/daemonsets/{name}" :put
             {:path-params   {"name" name "namespace" namespace }
              :header-params {}
              :query-params  {"pretty" pretty }
              :form-params   {}
              :body-param    body
              :content-types []
              :accepts       ["application/json" "application/yaml" "application/vnd.kubernetes.protobuf"]
              :auth-names    ["BearerToken"]})))

(defn-spec replace-namespaced-daemon-set v1beta1/daemon-set-spec
  "
  replace the specified DaemonSet"
  ([name string?, namespace string?, body v1beta1/daemon-set, ] (replace-namespaced-daemon-set name namespace body nil))
  ([name string?, namespace string?, body v1beta1/daemon-set, optional-params any?]
   (let [res (:data (replace-namespaced-daemon-set-with-http-info name namespace body optional-params))]
     (if (:decode-models *api-context*)
        (st/decode v1beta1/daemon-set-spec res st/string-transformer)
        res))))


(defn-spec replace-namespaced-daemon-set-status-with-http-info any?
  "
  replace status of the specified DaemonSet"
  ([name string?, namespace string?, body v1beta1/daemon-set, ] (replace-namespaced-daemon-set-status-with-http-info name namespace body nil))
  ([name string?, namespace string?, body v1beta1/daemon-set, {:keys [pretty]} (s/map-of keyword? any?)]
   (check-required-params name namespace body)
   (call-api "/apis/extensions/v1beta1/namespaces/{namespace}/daemonsets/{name}/status" :put
             {:path-params   {"name" name "namespace" namespace }
              :header-params {}
              :query-params  {"pretty" pretty }
              :form-params   {}
              :body-param    body
              :content-types []
              :accepts       ["application/json" "application/yaml" "application/vnd.kubernetes.protobuf"]
              :auth-names    ["BearerToken"]})))

(defn-spec replace-namespaced-daemon-set-status v1beta1/daemon-set-spec
  "
  replace status of the specified DaemonSet"
  ([name string?, namespace string?, body v1beta1/daemon-set, ] (replace-namespaced-daemon-set-status name namespace body nil))
  ([name string?, namespace string?, body v1beta1/daemon-set, optional-params any?]
   (let [res (:data (replace-namespaced-daemon-set-status-with-http-info name namespace body optional-params))]
     (if (:decode-models *api-context*)
        (st/decode v1beta1/daemon-set-spec res st/string-transformer)
        res))))


(defn-spec replace-namespaced-deployment-with-http-info any?
  "
  replace the specified Deployment"
  ([name string?, namespace string?, body extensions/v1beta1/deployment, ] (replace-namespaced-deployment-with-http-info name namespace body nil))
  ([name string?, namespace string?, body extensions/v1beta1/deployment, {:keys [pretty]} (s/map-of keyword? any?)]
   (check-required-params name namespace body)
   (call-api "/apis/extensions/v1beta1/namespaces/{namespace}/deployments/{name}" :put
             {:path-params   {"name" name "namespace" namespace }
              :header-params {}
              :query-params  {"pretty" pretty }
              :form-params   {}
              :body-param    body
              :content-types []
              :accepts       ["application/json" "application/yaml" "application/vnd.kubernetes.protobuf"]
              :auth-names    ["BearerToken"]})))

(defn-spec replace-namespaced-deployment extensions/v1beta1/deployment-spec
  "
  replace the specified Deployment"
  ([name string?, namespace string?, body extensions/v1beta1/deployment, ] (replace-namespaced-deployment name namespace body nil))
  ([name string?, namespace string?, body extensions/v1beta1/deployment, optional-params any?]
   (let [res (:data (replace-namespaced-deployment-with-http-info name namespace body optional-params))]
     (if (:decode-models *api-context*)
        (st/decode extensions/v1beta1/deployment-spec res st/string-transformer)
        res))))


(defn-spec replace-namespaced-deployment-scale-with-http-info any?
  "
  replace scale of the specified Deployment"
  ([name string?, namespace string?, body extensions/v1beta1/scale, ] (replace-namespaced-deployment-scale-with-http-info name namespace body nil))
  ([name string?, namespace string?, body extensions/v1beta1/scale, {:keys [pretty]} (s/map-of keyword? any?)]
   (check-required-params name namespace body)
   (call-api "/apis/extensions/v1beta1/namespaces/{namespace}/deployments/{name}/scale" :put
             {:path-params   {"name" name "namespace" namespace }
              :header-params {}
              :query-params  {"pretty" pretty }
              :form-params   {}
              :body-param    body
              :content-types []
              :accepts       ["application/json" "application/yaml" "application/vnd.kubernetes.protobuf"]
              :auth-names    ["BearerToken"]})))

(defn-spec replace-namespaced-deployment-scale extensions/v1beta1/scale-spec
  "
  replace scale of the specified Deployment"
  ([name string?, namespace string?, body extensions/v1beta1/scale, ] (replace-namespaced-deployment-scale name namespace body nil))
  ([name string?, namespace string?, body extensions/v1beta1/scale, optional-params any?]
   (let [res (:data (replace-namespaced-deployment-scale-with-http-info name namespace body optional-params))]
     (if (:decode-models *api-context*)
        (st/decode extensions/v1beta1/scale-spec res st/string-transformer)
        res))))


(defn-spec replace-namespaced-deployment-status-with-http-info any?
  "
  replace status of the specified Deployment"
  ([name string?, namespace string?, body extensions/v1beta1/deployment, ] (replace-namespaced-deployment-status-with-http-info name namespace body nil))
  ([name string?, namespace string?, body extensions/v1beta1/deployment, {:keys [pretty]} (s/map-of keyword? any?)]
   (check-required-params name namespace body)
   (call-api "/apis/extensions/v1beta1/namespaces/{namespace}/deployments/{name}/status" :put
             {:path-params   {"name" name "namespace" namespace }
              :header-params {}
              :query-params  {"pretty" pretty }
              :form-params   {}
              :body-param    body
              :content-types []
              :accepts       ["application/json" "application/yaml" "application/vnd.kubernetes.protobuf"]
              :auth-names    ["BearerToken"]})))

(defn-spec replace-namespaced-deployment-status extensions/v1beta1/deployment-spec
  "
  replace status of the specified Deployment"
  ([name string?, namespace string?, body extensions/v1beta1/deployment, ] (replace-namespaced-deployment-status name namespace body nil))
  ([name string?, namespace string?, body extensions/v1beta1/deployment, optional-params any?]
   (let [res (:data (replace-namespaced-deployment-status-with-http-info name namespace body optional-params))]
     (if (:decode-models *api-context*)
        (st/decode extensions/v1beta1/deployment-spec res st/string-transformer)
        res))))


(defn-spec replace-namespaced-ingress-with-http-info any?
  "
  replace the specified Ingress"
  ([name string?, namespace string?, body v1beta1/ingress, ] (replace-namespaced-ingress-with-http-info name namespace body nil))
  ([name string?, namespace string?, body v1beta1/ingress, {:keys [pretty]} (s/map-of keyword? any?)]
   (check-required-params name namespace body)
   (call-api "/apis/extensions/v1beta1/namespaces/{namespace}/ingresses/{name}" :put
             {:path-params   {"name" name "namespace" namespace }
              :header-params {}
              :query-params  {"pretty" pretty }
              :form-params   {}
              :body-param    body
              :content-types []
              :accepts       ["application/json" "application/yaml" "application/vnd.kubernetes.protobuf"]
              :auth-names    ["BearerToken"]})))

(defn-spec replace-namespaced-ingress v1beta1/ingress-spec
  "
  replace the specified Ingress"
  ([name string?, namespace string?, body v1beta1/ingress, ] (replace-namespaced-ingress name namespace body nil))
  ([name string?, namespace string?, body v1beta1/ingress, optional-params any?]
   (let [res (:data (replace-namespaced-ingress-with-http-info name namespace body optional-params))]
     (if (:decode-models *api-context*)
        (st/decode v1beta1/ingress-spec res st/string-transformer)
        res))))


(defn-spec replace-namespaced-ingress-status-with-http-info any?
  "
  replace status of the specified Ingress"
  ([name string?, namespace string?, body v1beta1/ingress, ] (replace-namespaced-ingress-status-with-http-info name namespace body nil))
  ([name string?, namespace string?, body v1beta1/ingress, {:keys [pretty]} (s/map-of keyword? any?)]
   (check-required-params name namespace body)
   (call-api "/apis/extensions/v1beta1/namespaces/{namespace}/ingresses/{name}/status" :put
             {:path-params   {"name" name "namespace" namespace }
              :header-params {}
              :query-params  {"pretty" pretty }
              :form-params   {}
              :body-param    body
              :content-types []
              :accepts       ["application/json" "application/yaml" "application/vnd.kubernetes.protobuf"]
              :auth-names    ["BearerToken"]})))

(defn-spec replace-namespaced-ingress-status v1beta1/ingress-spec
  "
  replace status of the specified Ingress"
  ([name string?, namespace string?, body v1beta1/ingress, ] (replace-namespaced-ingress-status name namespace body nil))
  ([name string?, namespace string?, body v1beta1/ingress, optional-params any?]
   (let [res (:data (replace-namespaced-ingress-status-with-http-info name namespace body optional-params))]
     (if (:decode-models *api-context*)
        (st/decode v1beta1/ingress-spec res st/string-transformer)
        res))))


(defn-spec replace-namespaced-network-policy-with-http-info any?
  "
  replace the specified NetworkPolicy"
  ([name string?, namespace string?, body v1beta1/network-policy, ] (replace-namespaced-network-policy-with-http-info name namespace body nil))
  ([name string?, namespace string?, body v1beta1/network-policy, {:keys [pretty]} (s/map-of keyword? any?)]
   (check-required-params name namespace body)
   (call-api "/apis/extensions/v1beta1/namespaces/{namespace}/networkpolicies/{name}" :put
             {:path-params   {"name" name "namespace" namespace }
              :header-params {}
              :query-params  {"pretty" pretty }
              :form-params   {}
              :body-param    body
              :content-types []
              :accepts       ["application/json" "application/yaml" "application/vnd.kubernetes.protobuf"]
              :auth-names    ["BearerToken"]})))

(defn-spec replace-namespaced-network-policy v1beta1/network-policy-spec
  "
  replace the specified NetworkPolicy"
  ([name string?, namespace string?, body v1beta1/network-policy, ] (replace-namespaced-network-policy name namespace body nil))
  ([name string?, namespace string?, body v1beta1/network-policy, optional-params any?]
   (let [res (:data (replace-namespaced-network-policy-with-http-info name namespace body optional-params))]
     (if (:decode-models *api-context*)
        (st/decode v1beta1/network-policy-spec res st/string-transformer)
        res))))


(defn-spec replace-namespaced-replica-set-with-http-info any?
  "
  replace the specified ReplicaSet"
  ([name string?, namespace string?, body v1beta1/replica-set, ] (replace-namespaced-replica-set-with-http-info name namespace body nil))
  ([name string?, namespace string?, body v1beta1/replica-set, {:keys [pretty]} (s/map-of keyword? any?)]
   (check-required-params name namespace body)
   (call-api "/apis/extensions/v1beta1/namespaces/{namespace}/replicasets/{name}" :put
             {:path-params   {"name" name "namespace" namespace }
              :header-params {}
              :query-params  {"pretty" pretty }
              :form-params   {}
              :body-param    body
              :content-types []
              :accepts       ["application/json" "application/yaml" "application/vnd.kubernetes.protobuf"]
              :auth-names    ["BearerToken"]})))

(defn-spec replace-namespaced-replica-set v1beta1/replica-set-spec
  "
  replace the specified ReplicaSet"
  ([name string?, namespace string?, body v1beta1/replica-set, ] (replace-namespaced-replica-set name namespace body nil))
  ([name string?, namespace string?, body v1beta1/replica-set, optional-params any?]
   (let [res (:data (replace-namespaced-replica-set-with-http-info name namespace body optional-params))]
     (if (:decode-models *api-context*)
        (st/decode v1beta1/replica-set-spec res st/string-transformer)
        res))))


(defn-spec replace-namespaced-replica-set-scale-with-http-info any?
  "
  replace scale of the specified ReplicaSet"
  ([name string?, namespace string?, body extensions/v1beta1/scale, ] (replace-namespaced-replica-set-scale-with-http-info name namespace body nil))
  ([name string?, namespace string?, body extensions/v1beta1/scale, {:keys [pretty]} (s/map-of keyword? any?)]
   (check-required-params name namespace body)
   (call-api "/apis/extensions/v1beta1/namespaces/{namespace}/replicasets/{name}/scale" :put
             {:path-params   {"name" name "namespace" namespace }
              :header-params {}
              :query-params  {"pretty" pretty }
              :form-params   {}
              :body-param    body
              :content-types []
              :accepts       ["application/json" "application/yaml" "application/vnd.kubernetes.protobuf"]
              :auth-names    ["BearerToken"]})))

(defn-spec replace-namespaced-replica-set-scale extensions/v1beta1/scale-spec
  "
  replace scale of the specified ReplicaSet"
  ([name string?, namespace string?, body extensions/v1beta1/scale, ] (replace-namespaced-replica-set-scale name namespace body nil))
  ([name string?, namespace string?, body extensions/v1beta1/scale, optional-params any?]
   (let [res (:data (replace-namespaced-replica-set-scale-with-http-info name namespace body optional-params))]
     (if (:decode-models *api-context*)
        (st/decode extensions/v1beta1/scale-spec res st/string-transformer)
        res))))


(defn-spec replace-namespaced-replica-set-status-with-http-info any?
  "
  replace status of the specified ReplicaSet"
  ([name string?, namespace string?, body v1beta1/replica-set, ] (replace-namespaced-replica-set-status-with-http-info name namespace body nil))
  ([name string?, namespace string?, body v1beta1/replica-set, {:keys [pretty]} (s/map-of keyword? any?)]
   (check-required-params name namespace body)
   (call-api "/apis/extensions/v1beta1/namespaces/{namespace}/replicasets/{name}/status" :put
             {:path-params   {"name" name "namespace" namespace }
              :header-params {}
              :query-params  {"pretty" pretty }
              :form-params   {}
              :body-param    body
              :content-types []
              :accepts       ["application/json" "application/yaml" "application/vnd.kubernetes.protobuf"]
              :auth-names    ["BearerToken"]})))

(defn-spec replace-namespaced-replica-set-status v1beta1/replica-set-spec
  "
  replace status of the specified ReplicaSet"
  ([name string?, namespace string?, body v1beta1/replica-set, ] (replace-namespaced-replica-set-status name namespace body nil))
  ([name string?, namespace string?, body v1beta1/replica-set, optional-params any?]
   (let [res (:data (replace-namespaced-replica-set-status-with-http-info name namespace body optional-params))]
     (if (:decode-models *api-context*)
        (st/decode v1beta1/replica-set-spec res st/string-transformer)
        res))))


(defn-spec replace-namespaced-replication-controller-dummy-scale-with-http-info any?
  "
  replace scale of the specified ReplicationControllerDummy"
  ([name string?, namespace string?, body extensions/v1beta1/scale, ] (replace-namespaced-replication-controller-dummy-scale-with-http-info name namespace body nil))
  ([name string?, namespace string?, body extensions/v1beta1/scale, {:keys [pretty]} (s/map-of keyword? any?)]
   (check-required-params name namespace body)
   (call-api "/apis/extensions/v1beta1/namespaces/{namespace}/replicationcontrollers/{name}/scale" :put
             {:path-params   {"name" name "namespace" namespace }
              :header-params {}
              :query-params  {"pretty" pretty }
              :form-params   {}
              :body-param    body
              :content-types []
              :accepts       ["application/json" "application/yaml" "application/vnd.kubernetes.protobuf"]
              :auth-names    ["BearerToken"]})))

(defn-spec replace-namespaced-replication-controller-dummy-scale extensions/v1beta1/scale-spec
  "
  replace scale of the specified ReplicationControllerDummy"
  ([name string?, namespace string?, body extensions/v1beta1/scale, ] (replace-namespaced-replication-controller-dummy-scale name namespace body nil))
  ([name string?, namespace string?, body extensions/v1beta1/scale, optional-params any?]
   (let [res (:data (replace-namespaced-replication-controller-dummy-scale-with-http-info name namespace body optional-params))]
     (if (:decode-models *api-context*)
        (st/decode extensions/v1beta1/scale-spec res st/string-transformer)
        res))))


(defn-spec replace-pod-security-policy-with-http-info any?
  "
  replace the specified PodSecurityPolicy"
  ([name string?, body v1beta1/pod-security-policy, ] (replace-pod-security-policy-with-http-info name body nil))
  ([name string?, body v1beta1/pod-security-policy, {:keys [pretty]} (s/map-of keyword? any?)]
   (check-required-params name body)
   (call-api "/apis/extensions/v1beta1/podsecuritypolicies/{name}" :put
             {:path-params   {"name" name }
              :header-params {}
              :query-params  {"pretty" pretty }
              :form-params   {}
              :body-param    body
              :content-types []
              :accepts       ["application/json" "application/yaml" "application/vnd.kubernetes.protobuf"]
              :auth-names    ["BearerToken"]})))

(defn-spec replace-pod-security-policy v1beta1/pod-security-policy-spec
  "
  replace the specified PodSecurityPolicy"
  ([name string?, body v1beta1/pod-security-policy, ] (replace-pod-security-policy name body nil))
  ([name string?, body v1beta1/pod-security-policy, optional-params any?]
   (let [res (:data (replace-pod-security-policy-with-http-info name body optional-params))]
     (if (:decode-models *api-context*)
        (st/decode v1beta1/pod-security-policy-spec res st/string-transformer)
        res))))


