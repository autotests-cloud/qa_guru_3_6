var content = "[";
$(".table-responsive tbody tr").each(function(i, element) {
    content += "{\"label\":\"" + $(element).find("td").first().text() +"\",";
    content += "\"value\":\"" + $(element).find("td").last().text() +"\"},";
});
content += "]";
return content.replace("},]", "}]").replace("  ", " ");
