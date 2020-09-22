package com.martinsgms.pyxis.utils;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.Month;
import java.time.YearMonth;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.martinsgms.pyxis.bean.ComunidadesParoquiaisEnum;
import com.martinsgms.pyxis.bean.Missa;
import com.martinsgms.pyxis.repository.MissaRepository;

@Component
public class ScheduleBrain {

	@Autowired
	private MissaRepository missaRepository;
	
	private final LocalDate now = LocalDate.now();
	private final Integer year = now.getYear();
	
	public void generateShceduleOfNextMonth() {
		
		Month nextMonth = now.getMonth().plus(1l);
		List<LocalDate> daysOfMass = filterMassDaysOf(nextMonth);
		
		fixWeekendInLastDayOfMonthTrouble(nextMonth, daysOfMass);
		
		if (checkIfMonthWasAlreadySaved(daysOfMass).size() > 0)
			throw new RuntimeException("Este mês já foi gravado");
		
		Map<DayOfWeek, Map<ComunidadesParoquiaisEnum, List<LocalTime>>> placesAndSchedulesOfMass = placesAndSchedulesOfMass();
		
		placesAndSchedulesOfMass.keySet().forEach(dayOfWeek -> 
			daysOfMass.stream().filter(date -> is(date, dayOfWeek)).forEach(day -> 
				placesAndSchedulesOfMass.get(dayOfWeek).forEach((place, schedules) ->
					schedules.forEach(schedule -> missaRepository.save(new Missa(place, LocalDateTime.of(day, schedule))))
				)
			)
		);
	}

	private List<Missa> checkIfMonthWasAlreadySaved(List<LocalDate> daysOfMass) {
		return missaRepository.findDataByDataGreaterThanAndDataLessThan(LocalDateTime.of(daysOfMass.get(0), LocalTime.of(00, 00)), LocalDateTime.of(daysOfMass.get(daysOfMass.size() - 1), LocalTime.of(23, 59)));
	}

	private void fixWeekendInLastDayOfMonthTrouble(Month nextMonth, List<LocalDate> daysOfMass) {
		if ((daysOfMass.size() % 2) != 0)
			daysOfMass.add(LocalDate.of(year, nextMonth.plus(1), 1));
		
		if (daysOfMass.get(0).getDayOfMonth() == 1)
			daysOfMass.remove(daysOfMass.get(0));
	}

	private List<LocalDate> filterMassDaysOf(Month month) {
		return IntStream.rangeClosed(1, YearMonth.of(year, month).lengthOfMonth())
				.mapToObj(day -> LocalDate.of(year, month, day))
				.filter(date -> isMassDay(date))
				.collect(Collectors.toList());
	}
	
	@SuppressWarnings("serial")
	private Map<DayOfWeek, Map<ComunidadesParoquiaisEnum, List<LocalTime>>> placesAndSchedulesOfMass() {
		return new HashMap<DayOfWeek, Map<ComunidadesParoquiaisEnum, List<LocalTime>>>() {{
			put(DayOfWeek.WEDNESDAY, new HashMap<ComunidadesParoquiaisEnum, List<LocalTime>>() {{ 
				put(ComunidadesParoquiaisEnum.MATRIZ, Arrays.asList(LocalTime.of(20, 00)));
			}});
			put(DayOfWeek.SATURDAY, new HashMap<ComunidadesParoquiaisEnum, List<LocalTime>>() {{ 
				put(ComunidadesParoquiaisEnum.GRUTA, Arrays.asList(LocalTime.of(16, 00)));
			}});
			put(DayOfWeek.SUNDAY, new HashMap<ComunidadesParoquiaisEnum, List<LocalTime>>() {{ 
				put(ComunidadesParoquiaisEnum.MATRIZ, Arrays.asList(LocalTime.of(9, 00), LocalTime.of(19, 30)));
				put(ComunidadesParoquiaisEnum.GRUTA, Arrays.asList(LocalTime.of(11, 00)));
			}});
		}};
	}
	
	private boolean isMassDay(LocalDate date) {
		return is(date, DayOfWeek.SATURDAY) 
				|| is(date, DayOfWeek.SUNDAY)
				|| is(date, DayOfWeek.WEDNESDAY);
	}
	
	private boolean is(LocalDate date, DayOfWeek dayOfWeek) {
		return date.getDayOfWeek().equals(dayOfWeek);
	}
}
