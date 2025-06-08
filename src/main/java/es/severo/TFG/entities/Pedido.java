package es.severo.TFG.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@ToString
@Entity
@Table(name = "pedidos")
public class Pedido {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "fecha", nullable = false)
    private LocalDateTime fecha;

    @Column(name = "numero_pedido_del_dia", nullable = false)
    private int numeroPedidoDelDia;

    @Column(name = "tipo", nullable = false)
    private String tipo;

    @Column(name = "estado", nullable = false)
    private String estado;

    @Column(name = "precio_total", nullable = false)
    private Double precioTotal;

    @Column(name = "precio_pagado", nullable = false)
    private Double precioPagado;

    @Column(name = "metodo_pago")
    private String metodoPago;

    //Los tickets puieden ser tan sumamente largos que tengo que especificar el valor para que no sea un varhcar(255)
    @Lob
    @Column(name = "ticket", nullable = false, columnDefinition = "LONGTEXT")
    private String ticket;

    @ManyToOne
    @JoinColumn(name = "mesa_id")
    private Mesa mesa;

    @ManyToOne
    @JoinColumn(name = "restaurante_id", nullable = false)
    private Restaurante restaurante;

    @OneToMany(mappedBy = "pedido")
    private Set<PedidoProducto> productos = new HashSet<>();
}
