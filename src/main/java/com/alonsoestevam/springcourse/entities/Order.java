package com.alonsoestevam.springcourse.entities;


import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;

import java.io.Serializable;
import java.time.Instant;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Entity
@Table(name = "tb_order")
public class Order implements Serializable{

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss'Z'", timezone = "GMT")
    private Instant moment;

    private Integer orderStatus;

    @ManyToOne
    /* isso diz que na tabela "tb_orders" no banco de dados teremos uma
     chave estrangeira chamada "client_id" que contém o id do user
    associado a esse pedido.
    Um cliente pode fazer vários pedidos => MANY (pedidos) to ONE (cliente) */
    @JoinColumn(name = "client_id")
    private User client;

    // @OneToMany porque um Pedido pode ter muitos Itens => ONE (pedido) to MANY (itens)
    // o OrderItem tem o atributo id, que é do tipo OrderItemPK (order e product)
    @OneToMany(mappedBy = "id.order") // como se fosse OrdemItem id = new OrderItem(); id.getOrder();
    // uma coleção de OrderItem associada a um Order
    private Set<OrderItem> items = new HashSet<>();

    /* Em relacionamentos 1:1, estamos mapeando as duas entidades para ter o mesmo id
    por exemplo, se um pedido tiver id = 5, o pagamento desse pedido também deve ter
    id = 5, daí a obrigatoriedade do cascade = CascadeType.ALL     */
    @OneToOne(mappedBy = "order", cascade = CascadeType.ALL)
    private Payment payment;

    public Order(){
    }

    public Order(Long id, Instant moment, OrderStatus orderStatus, User client) {
        this.id = id;
        this.moment = moment;
        setOrderStatus(orderStatus);
        this.client = client;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Instant getMoment() {
        return moment;
    }

    public void setMoment(Instant moment) {
        this.moment = moment;
    }

    public OrderStatus getOrderStatus() {
        return OrderStatus.valueOf(orderStatus);
    }

    public void setOrderStatus(OrderStatus orderStatus) {
        if (orderStatus != null){
            this.orderStatus = orderStatus.getCode();
        }
    }

    public User getClient() {
        return client;
    }

    public void setClient(User client) {
        this.client = client;
    }

    public Payment getPayment() {
        return payment;
    }

    public void setPayment(Payment payment) {
        this.payment = payment;
    }

    public Set<OrderItem> getItems(){
        return items;
    }

    // esse método apareceu no json de resposta como "total"
    public Double getTotal(){
        double sum = 0.0;
        for(OrderItem oi : items){
            sum += oi.getSubTotal();
        }
        return sum;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Order order = (Order) o;
        return Objects.equals(id, order.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
