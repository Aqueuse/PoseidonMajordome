import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/Search")
public class Search extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setContentType("text/html");
		response.getWriter()
				.append("<!DOCTYPE html>\n" + "<html>\n" + "<head>\n" + "<meta charset='ISO-8859-15'>\n"
						+ "<title>Rechercher</title>\n" + "</head>\n" + "<body>\n"
						+ "<div style='text-align: center;'>\n" + "<h1>Rechercher</h1>\n" + "<form method='POST'>\n"
						+ "<input type='text' name='entry' placeholder='mot cle'>\n"
						+ "<input type='submit' name='searchBtn' value='chercher'>\n" + "</form>\n" + "</div>\n"
						+ "</body>\n" + "</html>\n");
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String motCle = request.getParameter("entry");

		// transform the JSON to two arrays with essential words
		try {
			getJSONfromDistantAPI.grabJSON(motCle);
			extractJSON.extractWords(getJSONfromDistantAPI.JSONtree);
			// extractJSON.countThemAll(extractJSON.Words);
			// extractJSON.WordsFreqCount(extractJSON.finalWords, extractJSON.finalFreqs);

		} catch (Exception e) {
			response.getWriter().append(e + " in Search Class, doPost method");
		}

		response.getWriter().append(String.valueOf(extractJSON.finalWords.size()));

		// create the HTML cloud

		doGet(request, response);

		// if (extractJSON.finalWords.size() > 0) {
		// response.getWriter().append(HTMLPage.startPage(extractJSON.FreqArray.length,
		// extractJSON.minFreq));

		// for (int s = 0; s < extractJSON.finalWords.size(); s++) {
		// response.getWriter().append((HTMLPage.formatWord(extractJSON.finalWords.get(s)
		// + " ",
		// Integer.valueOf(extractJSON.finalFreqs.get(s)))));
		// }
		// response.getWriter().append(HTMLPage.endPage());

		// }

		// else {
		// response.getWriter().append("<p>Sorry, no cloud for this job :'(</p>");
		// }
	}
}