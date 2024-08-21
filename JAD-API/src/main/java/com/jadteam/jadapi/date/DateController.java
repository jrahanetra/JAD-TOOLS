package com.jadteam.jadapi.date;

import java.time.DateTimeException;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * DateController
 */
@RestController
@RequestMapping("/dates")
public class DateController {
    
	@GetMapping
    public List<LocalDate> findMondaysBetweenDates(@RequestParam String beginDate,
                                                   @RequestParam String endDate) {
        LocalDate beginLocalDate = LocalDate.parse(beginDate, DateTimeFormatter.ofPattern("dd-MM-yyyy"));
        LocalDate endLocalDate = LocalDate.parse(endDate, DateTimeFormatter.ofPattern("dd-MM-yyyy"));
        if (beginLocalDate.isAfter(endLocalDate))
            throw new DateTimeException("The beginDate is after the endDate");
        List<LocalDate> mondays = new ArrayList<>();
        while (!beginLocalDate.isEqual(endLocalDate.plusDays(1))) {
            if (beginLocalDate.getDayOfWeek().equals(DayOfWeek.MONDAY)) {
                mondays.add(beginLocalDate);
            }
            beginLocalDate = beginLocalDate.plusDays(1);
        }
        return mondays;
    }
}
