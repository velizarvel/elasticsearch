/*
 * Copyright Elasticsearch B.V. and/or licensed to Elasticsearch B.V. under one
 * or more contributor license agreements. Licensed under the Elastic License
 * 2.0; you may not use this file except in compliance with the Elastic License
 * 2.0.
 */
package org.elasticsearch.repositories.blobstore.testkit;

import org.elasticsearch.common.settings.Settings;
import org.elasticsearch.test.junit.annotations.TestIssueLogging;

import static org.hamcrest.Matchers.blankOrNullString;
import static org.hamcrest.Matchers.not;

public class S3SnapshotRepoTestKitIT extends AbstractSnapshotRepoTestKitRestTestCase {

    @Override
    protected String repositoryType() {
        return "s3";
    }

    @Override
    protected Settings repositorySettings() {
        final String bucket = System.getProperty("test.s3.bucket");
        assertThat(bucket, not(blankOrNullString()));

        final String basePath = System.getProperty("test.s3.base_path");
        assertThat(basePath, not(blankOrNullString()));

        return Settings.builder().put("client", "repo_test_kit").put("bucket", bucket).put("base_path", basePath).build();
    }

    @Override
    @TestIssueLogging(
        issueUrl = "https://github.com/elastic/elasticsearch/issues/102294",
        value = "org.elasticsearch.repositories.s3:TRACE,org.elasticsearch.repositories.blobstore.testkit:TRACE"
    )
    public void testRepositoryAnalysis() throws Exception {
        super.testRepositoryAnalysis();
    }
}
