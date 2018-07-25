package edu.khusaenov.example.helmestesttask.converter;

import static org.mockito.Mockito.when;

import edu.khusaenov.example.helmestesttask.HelmesTestTaskApplication;
import edu.khusaenov.example.helmestesttask.repository.SectorRepository;
import edu.khusaenov.example.helmestesttask.utils.DataCreator;
import java.util.NoSuchElementException;
import java.util.Optional;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @author Khusaenov on 25.07.2018
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = HelmesTestTaskApplication.class)
public class ConverterTest {

    private static final String TEST_SECTOR_ID = "2";
    @MockBean
    private SectorRepository sectorRepository;
    @Autowired
    private SectorConverter sectorConverter;

    @Test(expected = NoSuchElementException.class)
    public void whenIncomingSectorIdIsNullThenExceptionWillThrown() {
        sectorConverter.convert(null);
    }

    @Test(expected = NoSuchElementException.class)
    public void whenIncomingSectorIdIsEmptyThenExceptionWillThrown() {
        sectorConverter.convert("");
    }

    @Test(expected = NoSuchElementException.class)
    public void whenSectorWithIncomingIdNotExistThenExceptionWillThrown() {
        when(sectorRepository.findBySectorId(Long.parseLong(TEST_SECTOR_ID)))
                .thenReturn(Optional.empty());
        sectorConverter.convert(TEST_SECTOR_ID);
    }

    @Test
    public void whenSectorWithIncomingIdContainsInRepoThenReturnSector() {

        when(sectorRepository.findBySectorId(Long.parseLong(TEST_SECTOR_ID)))
                .thenReturn(Optional.of(new DataCreator().createSector(TEST_SECTOR_ID)));
        Assert.assertNotNull(sectorConverter.convert(TEST_SECTOR_ID));
    }


}
