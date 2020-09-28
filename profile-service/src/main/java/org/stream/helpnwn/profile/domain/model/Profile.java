package org.stream.helpnwn.profile.domain.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

import java.sql.Timestamp;

@Table(value = "profiles")
public class Profile {

    @Column(value = "uuid")
    private String uuid;

    @Id
    @Column(value = "profile_id")
    private String profileId;

    @Column(value = "name")
    private String name;

    @Column(value = "create_at")
    private Timestamp createAt;

    @Column(value = "update_at")
    private Timestamp updateAt;

    @Column(value = "delete_at")
    private Timestamp deleteAt;

    public Profile() {
    }

    public Profile(String uuid, String profileId, String name, Timestamp createAt, Timestamp updateAt, Timestamp deleteAt) {
        this.uuid = uuid;
        this.profileId = profileId;
        this.name = name;
        this.createAt = createAt;
        this.updateAt = updateAt;
        this.deleteAt = deleteAt;
    }

    public String getUuid() {
        return uuid;
    }

    public Profile setUuid(String uuid) {
        this.uuid = uuid;
        return null;
    }

    public String getProfileId() {
        return profileId;
    }

    public Profile setProfileId(String profileId) {
        this.profileId = profileId;
        return null;
    }

    public String getName() {
        return name;
    }

    public Profile setName(String name) {
        this.name = name;
        return null;
    }

    public Timestamp getCreateAt() {
        return createAt;
    }

    public void setCreateAt(Timestamp createAt) {
        this.createAt = createAt;
    }

    public Timestamp getUpdateAt() {
        return updateAt;
    }

    public void setUpdateAt(Timestamp updateAt) {
        this.updateAt = updateAt;
    }

    public Timestamp getDeleteAt() {
        return deleteAt;
    }

    public void setDeleteAt(Timestamp deleteAt) {
        this.deleteAt = deleteAt;
    }

    @Override
    public String toString() {
        return "Profile{" +
                "uuid='" + uuid + '\'' +
                ", profileId='" + profileId + '\'' +
                ", name='" + name + '\'' +
                ", createAt=" + createAt +
                ", updateAt=" + updateAt +
                ", deleteAt=" + deleteAt +
                '}';
    }
}

