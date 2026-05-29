package com.example.GestorEmpresaBeta.service;

import com.example.GestorEmpresaBeta.model.CompraProveedor;
import com.example.GestorEmpresaBeta.model.Gasto;
import com.example.GestorEmpresaBeta.model.Venta;
import com.example.GestorEmpresaBeta.repository.CompraProveedorRepository;
import com.example.GestorEmpresaBeta.repository.GastoRepository;
import com.example.GestorEmpresaBeta.repository.VentaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RequiredArgsConstructor
@Service
public class FinanzasService {
    private final VentaRepository ventaRepository;
    private final GastoRepository gastoRepository;
    private final CompraProveedorRepository compraProveedorRepository;
    //API para calcular el balance
    public Map<String, Double> balance(int mes, int anio) {
        try {
            double ganancias = calcularGanancias(mes, anio);
            double gastos = calcularGastos(mes, anio);
            double balance = ganancias - gastos;
            Map<String, Double> resultado = new HashMap<>();
            resultado.put("ganancias", ganancias);
            resultado.put("gastos", gastos);
            resultado.put("balance", balance);
            return resultado;
        } catch (Exception e) {
            throw new RuntimeException("Error al calcular el balance: " + e.getMessage(), e);
        }
    }
    //API para calcular ganancias por mes
    public Map<String, Double> gananciaMes(int mes, int anio) {
        try {
            Map<String, Double> resultado = new HashMap<>();
            resultado.put("ganancias", calcularGanancias(mes, anio));
            return resultado;
        } catch (Exception e) {
            throw new RuntimeException("Error al calcular ganancias: " + e.getMessage(), e);
        }
    }
    //API para calcular gastos por mes
    public Map<String, Double> gastosMes(int mes, int anio) {
        try {
            double gastos = calcularGastos(mes, anio);
            Map<String, Double> resultado = new HashMap<>();
            resultado.put("gastos", gastos);
            return resultado;
        } catch (Exception e) {
            throw new RuntimeException("Error al calcular gastos: " + e.getMessage(), e);
        }
    }
    //API para calcular ganancia del día
    public Map<String, Double> gananciaDia(LocalDate fecha) {
        try {
            List<Venta> ventas = ventaRepository.findByFecha(fecha);
            double total = ventas.stream()
                    .mapToDouble(Venta::getTotal)
                    .sum();
            Map<String, Double> resultado = new HashMap<>();
            resultado.put("ganancia", total);
            return resultado;
        } catch (Exception e) {
            throw new RuntimeException("Error al calcular ganancia del día: " + e.getMessage(), e);
        }
    }
    private double calcularGanancias(int mes, int anio) {
        List<Venta> ventas = ventaRepository.findByMesYAnio(mes, anio);
        return ventas.stream().mapToDouble(Venta::getTotal).sum();
    }

    private double calcularGastos(int mes, int anio) {
        List<Gasto> gastos = gastoRepository.findByMesYAnio(mes, anio);
        List<CompraProveedor> compras = compraProveedorRepository.findByMesYAnio(mes, anio);
        double totalGastos = gastos.stream().mapToDouble(Gasto::getMonto).sum();
        double totalCompras = compras.stream().mapToDouble(CompraProveedor::getTotal).sum();
        return totalGastos + totalCompras;
    }
}