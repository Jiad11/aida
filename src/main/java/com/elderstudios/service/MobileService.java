package com.elderstudios.service;

import com.elderstudios.domain.MobileServiceEntry;
import com.elderstudios.domain.MobileServiceEntryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MobileService {

    @Autowired
    protected MobileServiceEntryRepository mobileServiceEntryRepository;

    public List<MobileServiceEntry> findAll() {
        return mobileServiceEntryRepository.findAll();
    }

    public MobileServiceEntry save(MobileServiceEntry entry) {
        return mobileServiceEntryRepository.save(entry);
    }

    public void delete(Long id) {
        mobileServiceEntryRepository.delete(id);
    }

    public MobileServiceEntry findOne(Long id) {
        return mobileServiceEntryRepository.findOne(id);
    }
}
