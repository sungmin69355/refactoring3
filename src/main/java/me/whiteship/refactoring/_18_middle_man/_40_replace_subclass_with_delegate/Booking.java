package me.whiteship.refactoring._18_middle_man._40_replace_subclass_with_delegate;

import java.time.DayOfWeek;
import java.time.LocalDateTime;

public class Booking {

    protected Show show;

    protected LocalDateTime time;

    protected PremiumDelegate premiumDelegate;

    public Booking(Show show, LocalDateTime time) {
        this.show = show;
        this.time = time;
    }

    //생성자가 아닌 팩토리메서드를 사용하는이유 -> 메서드 이름을 자유롭게 사용가능, 객체의 리턴타입이 자유롭다.
    public static Booking createPremiumBook(Show show, LocalDateTime time, PremiumExtra extra) {
        Booking booking = new Booking(show, time);
        booking.premiumDelegate = new PremiumDelegate(booking, extra);
        return booking;
    }

    public static Booking createBooking(Show show, LocalDateTime time) {
        return new Booking(show, time);
    }


    public boolean hasTalkback() {
        return (this.premiumDelegate != null) ? this.premiumDelegate.hasTalkback() :
                this.show.hasOwnProperty("talkback") && !this.isPeakDay();
    }

    protected boolean isPeakDay() {
        DayOfWeek dayOfWeek = this.time.getDayOfWeek();
        return dayOfWeek == DayOfWeek.SATURDAY || dayOfWeek == DayOfWeek.SUNDAY;
    }

    public double basePrice() {
        double result = this.show.getPrice();
        if (this.isPeakDay()) result += Math.round(result * 0.15);
        return (this.premiumDelegate != null) ? this.premiumDelegate.extendBasePrice(result) : result;
    }

    public boolean hasDinner() {
        return (this.premiumDelegate != null && premiumDelegate.hasDinner());
    }
}
