package com.softtech.graduationproject.gen.service;

import com.softtech.graduationproject.gen.entity.BaseAdditionalFields;
import com.softtech.graduationproject.gen.entity.BaseEntity;
import com.softtech.graduationproject.gen.enums.GenErrorMessage;
import com.softtech.graduationproject.gen.exceptions.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public abstract class BaseEntityService <E extends BaseEntity, D extends JpaRepository<E, Long>> {

    private final D dao;

    public List<E> findAll(){
        return dao.findAll();
    }

    public Optional<E> findById(Long id){
        return dao.findById(id);
    }

    public E save(E entity){

        setAdditionalFields(entity);
        entity = dao.save(entity);

        return entity;
    }


    public void delete(E entity){
        dao.delete(entity);
    }

    public E getByIdWithControl(Long id) {

        Optional<E> entityOptional = findById(id);

        E entity;
        if (entityOptional.isPresent()){
            entity = entityOptional.get();
        } else {
            throw new EntityNotFoundException(GenErrorMessage.ENTITY_NOT_FOUND);
        }

        return entity;
    }

    public boolean existsById(Long id){
        return dao.existsById(id);
    }

    public D getDao() {
        return dao;
    }

    //TODO: control after jwt
    private Long getCurrentUserId() {
        Long currentUser = null;
        return currentUser;
    }

    private void setAdditionalFields(E entity) {

        BaseAdditionalFields baseAdditionalFields = entity.getBaseAdditionalFields();

        Long currentUserId = getCurrentUserId();

        if (baseAdditionalFields == null){
            baseAdditionalFields = new BaseAdditionalFields();
            entity.setBaseAdditionalFields(baseAdditionalFields);
        }

        if (entity.getId() == null){
            baseAdditionalFields.setCreateDate(new Date());
            baseAdditionalFields.setCreatedBy(currentUserId);
        }

        baseAdditionalFields.setUpdateDate(new Date());
        baseAdditionalFields.setUpdatedBy(currentUserId);
    }
}
