package cn.luim.platform.uaa.controller;

import cn.luim.boot.starter.base.model.Result;
import cn.luim.boot.starter.security.annotation.Anonymous;
import cn.luim.platform.uaa.model.command.ClientCreateCommand;
import cn.luim.platform.uaa.model.dto.ClientCreateDTO;
import cn.luim.platform.uaa.service.ClientService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author yang.lu
 */
@Anonymous
@RestController
@RequiredArgsConstructor
@RequestMapping("/client")
public class ClientController {

	private final ClientService clientService;

	@PostMapping
	public Result<ClientCreateDTO> create(@RequestBody ClientCreateCommand clientCreateCommand) {
		return Result.success(clientService.create(clientCreateCommand));
	}
}
