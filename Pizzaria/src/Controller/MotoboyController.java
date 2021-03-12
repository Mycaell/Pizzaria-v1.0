package Controller;


import DTO.PedidoDTO;
import Model.Motoboy;

public class MotoboyController {
	
	private Motoboy motoboy = new Motoboy();
	
	public PedidoDTO recuperarDadosDetodosPedidosProntosParaEntrega(){
		return motoboy.recuperarDadosDetodosPedidosProntosParaEntrega();
	}
	
	
	public void realizarEntregaDePedido(PedidoDTO pedidoDTO) {
		motoboy.realizarEntregaDePedido(pedidoDTO);
	}

}
