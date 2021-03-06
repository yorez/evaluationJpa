var turnManager = (function () {
	var getAll = function (obj, callback) {
		console.log("get All...");
		$.getJSON('../turns/' + obj, callback);
	};

	var add = function (obj, callback) {
		$.ajax({
			type: 'post',
			url: '../turns/' + obj.cno,
			data: JSON.stringify(obj),
			datatype: 'json',
			beforeSend: function (xhr) {
				xhr.setRequestHeader(obj.csrf.headerName, obj.csrf.token)
			},
			contentType: "application/json",
			success: callback
		});
	};

	var update = function (obj, callback) {
		console.log("update...");
		$.ajax({
			type: 'put',
			url: '../turns/' + obj.cno,
			dataType: 'json',
			data: JSON.stringify(obj),
			beforeSend: function (xhr) {
				xhr.setRequestHeader(obj.csrf.headerName, obj.csrf.token)
			},
			contentType: "application/json",
			success: callback
		});
	};

	var remove = function (obj, callback) {
		console.log("remove...");
		$.ajax({
			type: 'delete',
			url: '../turns/' + obj.cno + "/" + obj.tno,
			dataType: 'json',
			contentType: "application/json",
			beforeSend: function (xhr) {
				xhr.setRequestHeader(obj.csrf.headerName, obj.csrf.token)
			},
			success: callback
		});
	};

	return {
		getAll: getAll,
		add: add,
		update: update,
		remove: remove
	}
})();