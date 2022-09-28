package com.cs545.backend.service.impl;

import com.cs545.backend.config.impl.AuthenticationFacadeImpl;
import com.cs545.backend.dto.PropertyDto;
import com.cs545.backend.dto.PropertyWithRequestsDto;
import com.cs545.backend.entity.Owner;
import com.cs545.backend.entity.Property;
import com.cs545.backend.mapper.PropertyMapper;
import com.cs545.backend.mapper.PropertyWithRequestsMapper;
import com.cs545.backend.repository.PropertyRepo;
import com.cs545.backend.repository.OwnerRepo;
import com.cs545.backend.service.PropertyService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class PropertyServiceImpl implements PropertyService {
    private final PropertyRepo propertyRepo;
    private final PropertyMapper propertyMapper;
    private final PropertyWithRequestsMapper propertyWithRequestsMapper;
    private final AuthenticationFacadeImpl authentication;
    private final OwnerRepo ownerRepo;

    public PropertyServiceImpl(PropertyRepo propertyRepo, PropertyMapper propertyMapper, PropertyWithRequestsMapper propertyWithRequestsMapper, AuthenticationFacadeImpl authenticationFacade, OwnerRepo ownerRepo, OwnerRepo ownerRepo1) {
        this.propertyRepo = propertyRepo;
        this.propertyMapper = propertyMapper;
        this.propertyWithRequestsMapper = propertyWithRequestsMapper;
        this.ownerRepo = ownerRepo;
        this.authentication = AuthenticationFacadeImpl.getInstance();
    }

    @Override
    public void save(PropertyDto propertyDto) {
        if (!authentication.isOwner()) {
            return;
        }

        Owner owner = (Owner) authentication.getDetails();
        Property property = propertyMapper.toEntity(propertyDto);


        property.setOwner(owner);
        propertyRepo.save(property);
    }

    @Override
    public List<PropertyDto> findAll(Pageable pageable) {
        Page<Property> propertyPage = propertyRepo.findAll(pageable);

        if (propertyPage.hasContent()) {
            return propertyPage.getContent().stream().map(propertyMapper::toDto).toList();
        }

        return new ArrayList<>();
    }

    @Override
    public PropertyDto findById(long id) {
        Optional<Property> optionalProperty = propertyRepo.findById(id);

        if (optionalProperty.isEmpty()) {
            return null;
        }

        Property property = optionalProperty.get();

        boolean isAdminOrOwnerRole = authentication.isAdminOrOwnerRole();

        if (!isAdminOrOwnerRole) {
            updatePropertyViews(property);
        }

        return propertyMapper.toDto(property);
    }

    @Override
    public PropertyDto findByRequestId(long requestId) {
        if (!authentication.isOwner()) {
            return null;
        }

        Property property = propertyRepo.findByRequestId(requestId);
        return propertyMapper.toDto(property);
    }

    @Override
    public List<PropertyWithRequestsDto> findWithRequests(Pageable pageable) {
        if (!authentication.isOwner()) {
            return null;
        }

        Page<Property> propertyPage = propertyRepo.findAll(pageable);

        if (propertyPage.hasContent()) {
            return propertyPage.getContent().stream().map(propertyWithRequestsMapper::toDto).toList();
        }

        return new ArrayList<>();
    }


    @Override
    public void deleteById(long id) {
        boolean isAdminOrOwnerRole = authentication.isAdminOrOwnerRole();

        if (isAdminOrOwnerRole) {
            propertyRepo.deleteById(id);
        }
    }

    @Override
    public List<PropertyDto> findOwnerProperties(long ownerId, Pageable pageable) {
        Optional<Owner> ownerOptional = ownerRepo.findById(ownerId);

        if (ownerOptional.isEmpty()) {
            return new ArrayList<>();
        }

        Owner owner = ownerOptional.get();
        Page<Property> propertyPage = propertyRepo.findAllByOwner(owner, pageable);

        if (propertyPage.hasContent()) {
            return propertyPage.getContent().stream().map(propertyMapper::toDto).toList();
        }

        return new ArrayList<>();
    }

    private void updatePropertyViews(Property property) {
        int views = property.getViews();
        views += 1;
        property.setViews(views);
        propertyRepo.save(property);
    }
}
