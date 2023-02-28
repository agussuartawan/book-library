package com.dimata.service.general.model.entitiy;

import io.quarkus.hibernate.orm.panache.PanacheEntityBase;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Date;

@Entity
@Table(name = "returns")
public class Return extends PanacheEntityBase {

    public static final BigDecimal LATE_FEE_PERDAY = new BigDecimal(10000);

    @Id
    @GeneratedValue(generator = "dimata_id_gen")
    @Column(name = "return_id")
    public Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "borrow_id")
    public Borrow borrow;

    @Column(name = "return_date")
    public Date returnDate;

    @Column(name = "late_fee")
    public BigDecimal lateFee;

    @CreationTimestamp
    @Column(name = "created_at")
    public LocalDateTime createdAt;

    @UpdateTimestamp
    @Column(name = "updated_at")
    public LocalDateTime updatedAt;

}
