/*
CREATE TABLE IF NOT EXISTS revisions(
    rev BIGINT PRIMARY KEY,
    rev_hash varchar(255)
);

CREATE TABLE IF NOT EXISTS audits(
    id varchar(255) NOT NULL PRIMARY KEY,
    revision_number BIGINT NOT NULL REFERENCES revisions(rev),
    name varchar(255) NOT NULL,
    simple_class_name varchar(255) NOT NULL,
    qualified_class_name varchar(255) NOT NULL,
    audit_type varchar(100) NOT NULL,
    data TEXT NOT NULL,
    author varchar(255) NOT NULL,
    created_at timestamp NOT NULL
);
*/
