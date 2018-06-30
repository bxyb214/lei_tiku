package com.songzi.service;

import com.songzi.domain.Department;
import com.songzi.domain.Project;
import com.songzi.domain.enumeration.DeleteFlag;
import com.songzi.repository.ExamineRepository;
import com.songzi.repository.ProjectRepository;
import com.songzi.service.dto.ProjectDTO;
import com.songzi.service.mapper.ProjectMapper;
import com.songzi.service.mapper.ProjectVMMapper;
import com.songzi.web.rest.vm.ProjectQueryVM;
import com.songzi.web.rest.vm.ProjectVM;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Service class for managing users.
 */
@Service
@Transactional
public class ProjectService {

    private final Logger log = LoggerFactory.getLogger(ProjectService.class);

    private ProjectRepository projectRepository;
    private ExamineRepository examineRepository;
    private UserService userService;

    @Autowired
    private ProjectMapper projectMapper;

    @Autowired
    private ProjectVMMapper projectVMMapper;

    public ProjectService(ProjectRepository projectRepository, ExamineRepository examineRepository, UserService userService) {
        this.projectRepository = projectRepository;
        this.examineRepository = examineRepository;
        this.userService = userService;
    }

    /**
     * 新建项目
     * @param projectVM
     * @return
     */
    public Project insert(ProjectVM projectVM){
        Project project = projectVMMapper.toEntity(projectVM);

        project.setDelFlag(DeleteFlag.NORMAL);

        return projectRepository.save(project);
    }

    /**
     * 更新项目
     * @param projectVM
     * @return
     */
    public Project update(ProjectVM projectVM){
        Project project = projectRepository.findOne(projectVM.getId());

        project.setName(projectVM.getName());
        project.setStatus(projectVM.getStatus());
        project.setType(projectVM.getType());
        project.setDescription(projectVM.getDescription());
        project.setDuration(projectVM.getDuration());

        return projectRepository.save(project);
    }

    public Page<Project> findAll(ProjectQueryVM projectQueryVM, Pageable pageable){
        return projectRepository.findAll(new Specification<Project>() {
            @Override
            public Predicate toPredicate(Root<Project> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
                List<Predicate> list = new ArrayList<Predicate>();
                if(projectQueryVM.getName() != null && !"".equals(projectQueryVM.getName())){
                    list.add(cb.like(root.get("name").as(String.class), "%"+projectQueryVM.getName()+"%"));
                }
                if(projectQueryVM.getDate() != null){
                    list.add(cb.equal(root.get("createdDate").as(LocalDate.class), projectQueryVM.getDate()));
                }
                list.add(cb.equal(root.get("delFlag").as(String.class), DeleteFlag.NORMAL.name()));
                Predicate[] p = new Predicate[list.size()];
                return cb.and(list.toArray(p));
            }
        },pageable);
    }

    public Page<ProjectDTO> findAllWithExamine(Pageable pageable) {
        return projectRepository.findAllByDelFlagWithExamine(DeleteFlag.NORMAL, pageable);
    }
}
