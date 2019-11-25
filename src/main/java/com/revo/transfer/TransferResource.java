package com.revo.transfer;

import com.revo.core.OperationResult;
import com.revo.core.validation.ValidationResult;
import com.revo.transfer.validation.TransferValidator;

import javax.inject.Inject;
import javax.validation.Valid;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class TransferResource {

	private final TransferValidator transferValidator;
	private final TransferService transferService;

	@Inject
	public TransferResource(TransferValidator transferValidator, TransferService transferService) {
		this.transferValidator = transferValidator;
		this.transferService = transferService;
	}

	@POST
	@Path("/transfer")
	public Response transfer(@Valid TransferDto transferDto) {
		final ValidationResult validationResult = transferValidator.validate(transferDto);
		if (validationResult.isValid()) {
			transferService.transfer(transferDto);
		}
		return Response.ok(OperationResult.create(validationResult.isValid(), validationResult.getMessage())).build();
	}

}
