CREATE TABLE IF NOT EXISTS ORGANIZATIONS (
     ID UUID PRIMARY KEY,
     NAME VARCHAR,
     CREATED_AT BIGINT,
     UPDATED_AT BIGINT,
     CREATED_BY UUID,
     UPDATED_BY UUID
);

CREATE TABLE IF NOT EXISTS ROLES(
    ID UUID PRIMARY KEY,
    NAME VARCHAR UNIQUE,
    CREATED_AT BIGINT,
    UPDATED_AT BIGINT,
    CREATED_BY UUID,
    UPDATED_BY UUID
);

CREATE TABLE IF NOT EXISTS USERS(
    ID UUID PRIMARY KEY,
    NAME VARCHAR,
    EMAIL VARCHAR UNIQUE NOT NULL,
    PASSWORD VARCHAR,
    ORGANIZATION_ID UUID NOT NULL REFERENCES ORGANIZATIONS(ID),
    ROLE_ID UUID NOT NULL REFERENCES ROLES(ID),
    CREATED_AT BIGINT,
    UPDATED_AT BIGINT,
    CREATED_BY UUID,
    UPDATED_BY UUID
);

CREATE TABLE IF NOT EXISTS PROJECTS(
    ID UUID PRIMARY KEY,
    NAME VARCHAR NOT NULL,
    DESCRIPTION VARCHAR,
    ORGANIZATION_ID UUID NOT NULL REFERENCES ORGANIZATIONS(ID),
    CREATED_AT BIGINT,
    UPDATED_AT BIGINT,
    CREATED_BY UUID,
    UPDATED_BY UUID
);

CREATE TABLE IF NOT EXISTS TICKETS(
    ID UUID PRIMARY KEY,
    TITLE VARCHAR NOT NULL,
    DESCRIPTION VARCHAR,
    STATUS VARCHAR,
    PRIORITY INT,
    RESOLVED_AT BIGINT,
    CLOSED_AT BIGINT,
    PROJECT_ID UUID REFERENCES PROJECTS(ID),
    ASSIGNED_TO_USER_ID UUID REFERENCES USERS(ID),
    REPORT_TO_USER_ID UUID REFERENCES USERS(ID),
    --WATCHERS JSONB,
    CREATED_AT BIGINT,
    UPDATED_AT BIGINT,
    CREATED_BY UUID,
    UPDATED_BY UUID
);

CREATE TABLE IF NOT EXISTS COMMENTS(
    ID UUID PRIMARY KEY,
    CONTENT VARCHAR NOT NULL,
    TICKET_ID UUID REFERENCES TICKETS(ID),
    OWNER_USER_ID UUID REFERENCES USERS(ID),
    LATEST_COMMENTS JSONB,
    CREATED_AT BIGINT,
    UPDATED_AT BIGINT,
    CREATED_BY UUID,
    UPDATED_BY UUID
);

ALTER TABLE USERS ADD COLUMN VERIFIED_AT TIMESTAMP;
ALTER TABLE COMMENTS ADD COLUMN PARENT_COMMENT_ID UUID REFERENCES COMMENTS(ID);

