<h1><strong><span style="font-size:18px"># vecon<br />
Aplica&ccedil;&atilde;o de controle de ve&iacute;culos de usu&aacute;rios com base na API FIPE</span></strong></h1>

<h2><strong><span style="font-size:14px">Utiliza&ccedil;&atilde;o - exemplos:</span></strong></h2>

<ul>
	<li>
	<h3><strong><span style="color:#FFA500">POST usu&aacute;rio - http://localhost:8080/usuarios</span></strong></h3>
	</li>
</ul>

<p>{<br />
&nbsp; &nbsp; &quot;nome&quot;: &quot;Guilherme&quot;,<br />
&nbsp; &nbsp; &quot;cpf&quot;: &quot;123.456.789-12&quot;,<br />
&nbsp; &nbsp; &quot;email&quot;: &quot;test@email.com&quot;,<br />
&nbsp; &nbsp; &quot;dataNascimento&quot;: &quot;1994-03-19&quot;<br />
}</p>

<p><strong>Regras:</strong></p>

<ol>
	<li>Todos os campos obrgat&oacute;rios</li>
	<li>CPF e email, n&atilde;o podem ser repetidos</li>
	<li>formato da data de nascimento: &quot;YYYY-MM-dd&quot;</li>
</ol>

<ul>
	<li>
	<h3><strong><span style="color:#FFA500">POST ve&iacute;culo -&nbsp;http://localhost:8080/veiculos</span></strong></h3>
	</li>
</ul>

<p>{<br />
&nbsp; &nbsp; &quot;Marca&quot;: &quot;Fiat&quot;,<br />
&nbsp; &nbsp; &quot;Modelo&quot;: &quot;Siena elx&quot;,<br />
&nbsp; &nbsp; &quot;AnoModelo&quot;: &quot;2001&quot;,<br />
&nbsp; &nbsp; &quot;usuario&quot;: {<br />
&nbsp; &nbsp; &nbsp; &nbsp; &quot;id&quot;: 1<br />
&nbsp; &nbsp; }<br />
}</p>

<p><strong>Regras:</strong></p>

<ol>
	<li>Todos os campos obrgat&oacute;rios</li>
	<li>Marca, modelo e anoModelo devem existir na API FIPE(https://deividfortuna.github.io/fipe)</li>
	<li>Podem ser colocado nomes gen&eacute;ricos para pesquisa (Por exemplo: &quot;modelo&quot;: &quot;Siena ELX&quot;, retornar&aacute;: &quot;Siena&nbsp;ELX&nbsp;1.0&nbsp;mpi&nbsp;Fire&nbsp;16v&nbsp;4p&nbsp;(25&nbsp;anos)&quot;)</li>
	<li>Usu&aacute;rio deve estar cadastrado</li>
</ol>

<ul>
	<li>
	<h3><strong><span style="color:#FF8C00">GET usuario -&nbsp;http://localhost:8080/usuarios/{id}</span></strong></h3>
	Retorna um usu&aacute;rio espec&iacute;fico, coloque o numero do id do usu&aacute;rioo no {id}.</li>
</ul>
