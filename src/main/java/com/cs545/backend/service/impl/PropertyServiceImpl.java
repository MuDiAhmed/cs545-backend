package com.cs545.backend.service.impl;

import com.cs545.backend.config.impl.AuthenticationFacadeImpl;
import com.cs545.backend.dto.PropertyDto;
import com.cs545.backend.entity.Property;
import com.cs545.backend.mapper.PropertyMapper;
import com.cs545.backend.repository.PropertyRepo;
import com.cs545.backend.service.PropertyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PropertyServiceImpl implements PropertyService {
    private final PropertyRepo propertyRepo;
    private final PropertyMapper propertyMapper;
    private final AuthenticationFacadeImpl authenticationFacade;

    public PropertyServiceImpl(PropertyRepo propertyRepo, PropertyMapper propertyMapper, AuthenticationFacadeImpl authenticationFacade) {
        this.propertyRepo = propertyRepo;
        this.propertyMapper = propertyMapper;
        this.authenticationFacade = authenticationFacade;
    }

    @Override
    public void save(PropertyDto propertyDto, long ownerId) {
        Property property = propertyMapper.toEntity(propertyDto);
        propertyRepo.save(property);
    }

    @Override
    public List<Property> findAll(Pageable pageable) {
        return propertyRepo.findAll(pageable).toList();
    }

    @Override
    public PropertyDto findById(long id) {
        Optional<Property> optionalProperty = propertyRepo.findById(id);

        if (optionalProperty.isEmpty()) {
            return null;
        }

        Property property = optionalProperty.get();

        Authentication authentication = authenticationFacade.getAuthentication();
        boolean isAdminOrOwnerRole = authentication.getAuthorities().stream()
                .anyMatch(r -> {
                    String authority = r.getAuthority();
                    return authority.equalsIgnoreCase("admin") || authority.equalsIgnoreCase("owner");
                });

        if (!isAdminOrOwnerRole) {
            updatePropertyViews(property);
        }

        return propertyMapper.toDto(property);
    }

    @Override
    public void deleteById(long id) {
        propertyRepo.deleteById(id);
    }

    @Override
    public void update(PropertyDto propertyDto, long l) {
        // Todo - add any update operations that may be necessary
    }

    private void updatePropertyViews(Property property) {
        int views = property.getViews();
        views += 1;
        property.setViews(views);
        propertyRepo.save(property);
    }
}
