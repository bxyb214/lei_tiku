package com.songzi.domain;


import com.songzi.domain.enumeration.DeleteFlag;

import javax.persistence.*;
import javax.validation.constraints.*;

import java.io.Serializable;
import java.util.Objects;

/**
 * A Examine.
 */
@Entity
@Table(name = "examine")
public class Examine extends AbstractAuditingEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Column(name = "name", nullable = false)
    private String name;

    @NotNull
    @Column(name = "status", nullable = false)
    private String status;

    @NotNull
    @Enumerated(EnumType.STRING)
    @Column(name = "del_flag", nullable = false)
    private DeleteFlag delFlag;

    @NotNull
    @Column(name = "department_id", nullable = false)
    private Long departmentId;

    @NotNull
    @Column(name = "duration", nullable = false)
    private Integer duration;

    @Column(name = "score")
    private Integer score;

    @Column(name = "user_id", nullable = false)
    @NotNull
    private Long userId;

    @ManyToOne(optional = false)
    @JoinColumn(name = "project_id", nullable = false)
    @NotNull
    private Project project;

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public Examine name(String name) {
        this.name = name;
        return this;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getStatus() {
        return status;
    }

    public Examine status(String status) {
        this.status = status;
        return this;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public DeleteFlag getDelFlag() {
        return delFlag;
    }

    public Examine delFlag(DeleteFlag delFlag) {
        this.delFlag = delFlag;
        return this;
    }

    public void setDelFlag(DeleteFlag delFlag) {
        this.delFlag = delFlag;
    }

    public Long getDepartmentId() {
        return departmentId;
    }

    public Examine departmentId(Long departmentId) {
        this.departmentId = departmentId;
        return this;
    }

    public void setDepartmentId(Long departmentId) {
        this.departmentId = departmentId;
    }

    public Integer getDuration() {
        return duration;
    }

    public Examine duration(Integer duration) {
        this.duration = duration;
        return this;
    }

    public void setDuration(Integer duration) {
        this.duration = duration;
    }

    public Integer getScore() {
        return score;
    }

    public Examine score(Integer score) {
        this.score = score;
        return this;
    }

    public void setScore(Integer score) {
        this.score = score;
    }

    public Project getProject() {
        return project;
    }

    public Examine project(Project project) {
        this.project = project;
        return this;
    }

    public void setProject(Project project) {
        this.project = project;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here, do not remove

    public Examine UserId(Long userId) {
        this.userId = userId;
        return this;
    }
    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Examine examine = (Examine) o;
        if (examine.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), examine.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "Examine{" +
            "id=" + getId() +
            ", name='" + getName() + "'" +
            ", status='" + getStatus() + "'" +
            ", delFlag='" + getDelFlag() + "'" +
            ", departmentId=" + getDepartmentId() +
            ", duration=" + getDuration() +
            ", score=" + getScore() +
            ", projectId=" + getProject().toString() +
            "}";
    }
}