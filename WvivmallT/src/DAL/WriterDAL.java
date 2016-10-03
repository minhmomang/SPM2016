package DAL;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import Model.ItemTemplateCate;
import Model.ItemTopWriter;
import Model.WriterItem;

import EJB.IConnectEJBS;
public class WriterDAL {
	public static Map<String, Object> save_writer(WriterItem item, String type,
			String lang) {
		Map<String, Object> result = new HashMap<String, Object>();
		try {
			String spname = "sp_tbwriter_insert";
			String[] pfield = { "p_type", "f", "p_writer_id", "p_title",
					"p_description", "p_image", "p_creator", "p_modifier",
					"p_content", "p_lang", "p_cate" };
			String[] ptype = { "VARCHAR", "INT", "VARCHAR", "VARCHAR", "TEXT",
					"VARCHAR", "VARCHAR", "VARCHAR", "TEXT", "VARCHAR",
					"VARCHAR" };
			Object[] pvalue = { type, "", item.getWriter_id(), item.getTitle(),
					item.getDescription(), item.getImage(), item.getCreator(),
					item.getModifier(), item.getContent(), lang, item.getCate()

			};
			int[] pdirec = { 0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0

			};
			IConnectEJBS con = new IConnectEJBS();

			result = con.ExecBoFunctionReturnList(spname, pfield, ptype,
					pvalue, pdirec);

		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return result;
	}

	public static ArrayList<WriterItem> get_list_writer(String lang) {
		ArrayList<WriterItem> list = new ArrayList<WriterItem>();
		List<Map<String, Object>> listob = new ArrayList<Map<String, Object>>();
		try {

			String spname = "sp_writer_get";
			String[] pfield = { "p_lang" };
			Object[] pvalues = { lang };
			IConnectEJBS con = new IConnectEJBS();
			listob = con.ExecBoFunctionReturnResutlSet(spname, pfield, pvalues);
			for (int i = 0; i < listob.size(); i++) {
				Map<String, Object> temp = listob.get(i);
				WriterItem a = new WriterItem();
				a.setWriter_id(temp.get("writer_id").toString());
				a.setTitle(temp.get("title").toString());
				a.setDescription(temp.get("description").toString());
				a.setCreate_date(temp.get("create_date").toString());
				a.setCreator(temp.get("creator").toString());
				a.setModifier(temp.get("modifier") == null ? "" : temp.get(
						"modifier").toString());
				a.setModify_date(temp.get("modify_date") == null ? "" : temp
						.get("modify_date").toString());
				a.setVisible(temp.get("visible").toString());
				a.setCate(temp.get("namecate").toString());
				list.add(a);
			}

		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return list;
	}

	public static WriterItem get_writer_by_id(String writer_id, String lang) {
		String query = null;
		List<Map<String, Object>> listob = new ArrayList<Map<String, Object>>();
		WriterItem item = new WriterItem();
		try {
			query = "SELECT writer_id, title, description, image, content,cate FROM tb_writer where writer_id='"
					+ writer_id + "'" + "	and lang='" + lang + "'";
			IConnectEJBS con = new IConnectEJBS();
			listob = con.GetDataReturnResultSet(query);

			for (int i = 0; i < listob.size(); i++) {
				Map<String, Object> temp = listob.get(i);
				item.setWriter_id(temp.get("writer_id").toString());
				item.setTitle(temp.get("title").toString());
				item.setDescription(temp.get("description").toString());
				item.setImage(temp.get("image").toString());
				item.setContent(temp.get("content").toString());
				item.setCate(temp.get("cate").toString());
				break;
			}

		} catch (Exception ex) {
			////System.out.println(ex.getMessage());
		}
		return item;
	}

	public static Map<String, Object> delete_writer(String str_writer) {
		Map<String, Object> result = new HashMap<String, Object>();
		try {
			String spname = "sp_tbwriter_delete";
			String[] pfield = { "str_writer", "f"

			};
			String[] ptype = { "TEXT", "INT" };
			Object[] pvalue = { str_writer, ""

			};
			int[] pdirec = { 0, 1 };
			IConnectEJBS con = new IConnectEJBS();
			result = con.ExecBoFunctionReturnList(spname, pfield, ptype,
					pvalue, pdirec);

		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return result;
	}

	public static Map<String, Object> publish_writer(String str_writer,
			String publish, String lang) {
		Map<String, Object> result = new HashMap<String, Object>();
		try {
			String spname = "sp_tbwriter_publish";
			String[] pfield = { "str_writer", "p_publish", "p_lang", "f"

			};
			String[] ptype = { "TEXT", "VARCHAR", "VARCHAR", "INT" };
			Object[] pvalue = { str_writer, publish, lang, ""

			};
			int[] pdirec = { 0, 0, 0, 1 };
			IConnectEJBS con = new IConnectEJBS();
			result = con.ExecBoFunctionReturnList(spname, pfield, ptype,
					pvalue, pdirec);

		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return result;
	}

	public static ArrayList<WriterItem> get_writer_client(String lang) {
		ArrayList<WriterItem> list = new ArrayList<WriterItem>();
		String query;
		List<Map<String, Object>> listob = new ArrayList<Map<String, Object>>();

		try {
			query = "SELECT writer_id,title,description,image FROM tb_writer where visible=1 and lang='"
					+ lang + "' limit 0,8";
			IConnectEJBS con = new IConnectEJBS();
			listob = con.GetDataReturnResultSet(query);
			for (int i = 0; i < listob.size(); i++) {
				Map<String, Object> temp = listob.get(i);
				WriterItem a = new WriterItem();
				a.setWriter_id(temp.get("writer_id").toString());
				a.setTitle(temp.get("title").toString());
				a.setDescription(temp.get("description").toString());
				a.setCreate_date(temp.get("image").toString());
				list.add(a);
			}

		} catch (Exception ex) {
			////System.out.println(ex.getMessage());
		}
		return list;
	}

	public static ArrayList<WriterItem> get_writer_client_all(String lang) {
		ArrayList<WriterItem> list = new ArrayList<WriterItem>();
		String query;
		List<Map<String, Object>> listob = new ArrayList<Map<String, Object>>();

		try {
			query = "SELECT writer_id,title,description,image FROM tb_writer where visible=1 and lang='"
					+ lang + "'";
			IConnectEJBS con = new IConnectEJBS();
			listob = con.GetDataReturnResultSet(query);

			for (int i = 0; i < listob.size(); i++) {
				Map<String, Object> temp = listob.get(i);
				WriterItem a = new WriterItem();
				a.setWriter_id(temp.get("writer_id").toString());
				a.setTitle(temp.get("title").toString());
				a.setDescription(temp.get("description").toString());
				a.setCreate_date(temp.get("image").toString());
				list.add(a);
			}

		} catch (Exception ex) {
			////System.out.println(ex.getMessage());
		}
		return list;
	}

	public static ArrayList<Object> get_list_category() {
		String query = null;
		List<Map<String, Object>> listob = new ArrayList<Map<String, Object>>();
		ArrayList<Object> list = new ArrayList<Object>();
		try {
			query = "SELECT id,name FROM tb_writer_category";
			IConnectEJBS con = new IConnectEJBS();
			listob = con.GetDataReturnResultSet(query);

			for (int i = 0; i < listob.size(); i++) {
				Map<String, Object> temp = listob.get(i);
				ItemTemplateCate item = new ItemTemplateCate();
				item.setId(temp.get("id").toString());
				item.setName(temp.get("name").toString());

				list.add(item);
			}

		} catch (Exception ex) {
			////System.out.println(ex.getMessage());
		}
		return list;
	}

	public static ArrayList<Object> get_list_top_writer(String lang) {
		ArrayList<Object> list = new ArrayList<Object>();
		String query = "SELECT id,name FROM tb_writer_category;";
		List<Map<String, Object>> listob = new ArrayList<Map<String, Object>>();
		try {
			IConnectEJBS con = new IConnectEJBS();
			listob = con.GetDataReturnResultSet(query);
			ArrayList<ItemTemplateCate> list_cate = new ArrayList<ItemTemplateCate>();

			for (int i = 0; i < listob.size(); i++) {
				Map<String, Object> temp = listob.get(i);
				ItemTemplateCate item = new ItemTemplateCate();
				item.setId(temp.get("id").toString());
				item.setName(temp.get("name").toString());

				list.add(item);
			}

			for (ItemTemplateCate item : list_cate) {
				ArrayList<WriterItem> list_writer = new ArrayList<WriterItem>();
				list_writer = get_list_writer_by_cate(item.getId(), lang);
				if (list_writer.size() > 0) {
					ItemTopWriter itemadd = new ItemTopWriter();
					itemadd.setCategoryId(item.getId());
					itemadd.setCategoryName(item.getName());
					itemadd.setList(list_writer);
					list.add(itemadd);
				}
			}

		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return list;
	}

	public static ArrayList<WriterItem> get_list_writer_by_cate(String cateid,
			String lang) {
		ArrayList<WriterItem> list = new ArrayList<WriterItem>();
		String query;
		List<Map<String, Object>> listob = new ArrayList<Map<String, Object>>();

		try {
			query = "SELECT writer_id,title,description,image FROM tb_writer where visible=1 and lang='"
					+ lang + "' and cate='" + cateid + "' limit 0,4";
			IConnectEJBS con = new IConnectEJBS();
			listob = con.GetDataReturnResultSet(query);
			for (int i = 0; i < listob.size(); i++) {
				Map<String, Object> temp = listob.get(i);
				WriterItem item = new WriterItem();
				item.setWriter_id(temp.get("writer_id").toString());
				item.setTitle(temp.get("title").toString());
				item.setDescription(temp.get("description").toString());
				item.setImage(temp.get("image").toString());
				list.add(item);

			}

		} catch (Exception ex) {
			////System.out.println(ex.getMessage());
		}
		return list;
	}

	public static ArrayList<Object> get_list_top_writer_home(String lang) {
		ArrayList<Object> list = new ArrayList<Object>();
		String query = "SELECT id,name FROM tb_writer_category;";
		List<Map<String, Object>> listob = new ArrayList<Map<String, Object>>();
		try {
			IConnectEJBS con = new IConnectEJBS();
			listob = con.GetDataReturnResultSet(query);
			ArrayList<ItemTemplateCate> list_cate = new ArrayList<ItemTemplateCate>();

			for (int i = 0; i < listob.size(); i++) {
				Map<String, Object> temp = listob.get(i);
				ItemTemplateCate item = new ItemTemplateCate();
				item.setId(temp.get("id").toString());
				item.setName(temp.get("name").toString());
				list_cate.add(item);
			}

			for (ItemTemplateCate item : list_cate) {
				ArrayList<WriterItem> list_writer = new ArrayList<WriterItem>();
				list_writer = get_list_writer_by_cate(item.getId(), lang);
				if (list_writer.size() > 0) {
					if (list.size() < 2) {
						ItemTopWriter itemadd = new ItemTopWriter();
						itemadd.setCategoryId(item.getId());
						itemadd.setCategoryName(item.getName());
						itemadd.setList(list_writer);
						list.add(itemadd);
					} else {
						break;
					}
				}
			}

		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return list;
	}

	public static ArrayList<WriterItem> get_read_most(String lang) {
		ArrayList<WriterItem> list = new ArrayList<WriterItem>();
		String query;
		List<Map<String, Object>> listob = new ArrayList<Map<String, Object>>();

		try {
			IConnectEJBS con = new IConnectEJBS();
			query = "SELECT writer_id,title,description,image FROM tb_writer where visible=1 and lang='"
					+ lang + "' order by numread desc limit 0,5 ";
			listob = con.GetDataReturnResultSet(query);

			for (int i = 0; i < listob.size(); i++) {
				Map<String, Object> temp = listob.get(i);
				WriterItem item = new WriterItem();
				item.setWriter_id(temp.get("writer_id").toString());
				item.setTitle(temp.get("title").toString());
				item.setDescription(temp.get("description").toString());
				item.setImage(temp.get("image").toString());
				list.add(item);

			}

		} catch (Exception ex) {
			////System.out.println(ex.getMessage());
		}
		return list;
	}

	public static ArrayList<WriterItem> get_read_most_category(String lang,
			String cate) {
		ArrayList<WriterItem> list = new ArrayList<WriterItem>();
		String query;
		List<Map<String, Object>> listob = new ArrayList<Map<String, Object>>();

		try {
			IConnectEJBS con = new IConnectEJBS();
			query = "SELECT writer_id,title,description,image FROM tb_writer where cate = '"
					+ cate
					+ "' and visible=1 and lang='"
					+ lang
					+ "' order by numread desc limit 0,5 ";
			listob = con.GetDataReturnResultSet(query);

			for (int i = 0; i < listob.size(); i++) {
				Map<String, Object> temp = listob.get(i);
				WriterItem item = new WriterItem();
				item.setWriter_id(temp.get("writer_id").toString());
				item.setTitle(temp.get("title").toString());
				item.setDescription(temp.get("description").toString());
				item.setImage(temp.get("image").toString());
				list.add(item);

			}

		} catch (Exception ex) {
			////System.out.println(ex.getMessage());
		}
		return list;
	}

	public static ArrayList<WriterItem> get_read_more_category(String lang,
			String cate) {
		ArrayList<WriterItem> list = new ArrayList<WriterItem>();
		String query;
		List<Map<String, Object>> listob = new ArrayList<Map<String, Object>>();

		try {
			query = "SELECT writer_id,title,description,image FROM tb_writer where cate = '"
					+ cate + "' and visible=1 and lang='" + lang + "' ";
			IConnectEJBS con = new IConnectEJBS();
			listob = con.GetDataReturnResultSet(query);

			for (int i = 0; i < listob.size(); i++) {
				Map<String, Object> temp = listob.get(i);
				WriterItem item = new WriterItem();
				item.setWriter_id(temp.get("writer_id").toString());
				item.setTitle(temp.get("title").toString());
				item.setDescription(temp.get("description").toString());
				item.setImage(temp.get("image").toString());
				list.add(item);

			}

		} catch (Exception ex) {
			////System.out.println(ex.getMessage());
		}
		return list;
	}

	public static ArrayList<WriterItem> get_news(String lang) {
		ArrayList<WriterItem> list = new ArrayList<WriterItem>();
		String query;
		List<Map<String, Object>> listob = new ArrayList<Map<String, Object>>();

		try {
			query = "select * from tb_writer where visible=1 and lang='" + lang
					+ "' limit 9 ";
			IConnectEJBS con = new IConnectEJBS();
			listob = con.GetDataReturnResultSet(query);

			for (int i = 0; i < listob.size(); i++) {
				Map<String, Object> temp = listob.get(i);
				WriterItem item = new WriterItem();
				item.setWriter_id(temp.get("writer_id").toString());
				item.setTitle(temp.get("title").toString());
				item.setCreate_date(temp.get("create_date").toString());
				item.setDescription(temp.get("description").toString());
				item.setImage(temp.get("image").toString());
				item.setContent("content");
				list.add(item);

			}

		} catch (Exception ex) {
			////System.out.println(ex.getMessage());
		}
		return list;
	}

	public static ArrayList<ItemTopWriter> search_basic(String lang,
			String cate, String key) {
		ArrayList<ItemTopWriter> list = new ArrayList<ItemTopWriter>();
		String query;
		List<Map<String, Object>> listob = new ArrayList<Map<String, Object>>();
		if (key.isEmpty()) {
			key = "%%";
		}
		try {
			IConnectEJBS con = new IConnectEJBS();
			if (cate.toUpperCase().equals("ALL")) {
				// search writer
				query = "SELECT writer_id,title,description,image FROM tb_writer where visible=1 and lang='"
						+ lang + "' and title like '%" + key + "%'";

				listob = con.GetDataReturnResultSet(query);
				ItemTopWriter itemadd = new ItemTopWriter();
				itemadd.setCategoryId("write");
				ArrayList<WriterItem> listw = new ArrayList<WriterItem>();
				itemadd.setList(listw);
				for (int i = 0; i < listob.size(); i++) {
					Map<String, Object> temp = listob.get(i);
					WriterItem item = new WriterItem();
					item.setWriter_id(temp.get("writer_id").toString());
					item.setTitle(temp.get("title").toString());
					item.setDescription(temp.get("description").toString());
					item.setImage(temp.get("image").toString());
					itemadd.getList().add(item);

				}

				if (itemadd.getList().size() > 0) {
					list.add(itemadd);
				}
				// search service
				query = "SELECT service_id,title,description,image FROM tb_service where visible=1 and lang='"
						+ lang + "' and title like '%" + key + "%'";
				listob = con.GetDataReturnResultSet(query);
				ItemTopWriter itemadd1 = new ItemTopWriter();
				itemadd1.setCategoryId("service");
				ArrayList<WriterItem> listw1 = new ArrayList<WriterItem>();
				itemadd1.setList(listw1);

				for (int i = 0; i < listob.size(); i++) {
					Map<String, Object> temp = listob.get(i);
					WriterItem item = new WriterItem();
					item.setWriter_id(temp.get("writer_id").toString());
					item.setTitle(temp.get("title").toString());
					item.setDescription(temp.get("description").toString());
					item.setImage(temp.get("image").toString());
					itemadd1.getList().add(item);

				}

				if (itemadd1.getList().size() > 0) {
					list.add(itemadd1);
				}
			} else if (cate.toUpperCase().equals("SERVICE")) {// Service
				// search service
				query = "SELECT service_id,title,description,image FROM tb_service where visible=1 and lang='"
						+ lang + "' and title like '%" + key + "%'";
				listob = con.GetDataReturnResultSet(query);
				ItemTopWriter itemadd1 = new ItemTopWriter();
				itemadd1.setCategoryId("service");
				ArrayList<WriterItem> listw1 = new ArrayList<WriterItem>();
				itemadd1.setList(listw1);

				for (int i = 0; i < listob.size(); i++) {
					Map<String, Object> temp = listob.get(i);
					WriterItem item = new WriterItem();
					item.setWriter_id(temp.get("service_id").toString());
					item.setTitle(temp.get("title").toString());
					item.setDescription(temp.get("description").toString());
					item.setImage(temp.get("image").toString());
					itemadd1.getList().add(item);

				}

				if (itemadd1.getList().size() > 0) {
					list.add(itemadd1);
				}
			} else if (cate.toUpperCase().equals("WRITE")) {// writer
				// search writer
				query = "SELECT writer_id,title,description,image FROM tb_writer where visible=1 and lang='"
						+ lang + "' and title like '%" + key + "%'";
				listob = con.GetDataReturnResultSet(query);
				ItemTopWriter itemadd = new ItemTopWriter();
				itemadd.setCategoryId("write");
				ArrayList<WriterItem> listw = new ArrayList<WriterItem>();
				itemadd.setList(listw);

				for (int i = 0; i < listob.size(); i++) {
					Map<String, Object> temp = listob.get(i);
					WriterItem item = new WriterItem();
					item.setWriter_id(temp.get("writer_id").toString());
					item.setTitle(temp.get("title").toString());
					item.setDescription(temp.get("description").toString());
					item.setImage(temp.get("image").toString());
					itemadd.getList().add(item);

				}

				if (itemadd.getList().size() > 0) {
					list.add(itemadd);
				}
			}

		} catch (Exception ex) {
			////System.out.println(ex.getMessage());
		}
		return list;
	}

	public static ArrayList<WriterItem> get_news_via_cate(String lang,
			String cate) {
		ArrayList<WriterItem> list = new ArrayList<WriterItem>();
		String query;
		List<Map<String, Object>> listob = new ArrayList<Map<String, Object>>();

		try {
			query = "SELECT * FROM tb_writer where cate= "
					+ cate;
			IConnectEJBS con = new IConnectEJBS();
			listob = con.GetDataReturnResultSet(query);

			for (int i = 0; i < listob.size(); i++) {
				Map<String, Object> temp = listob.get(i);
				WriterItem item = new WriterItem();
				item.setWriter_id(temp.get("writer_id").toString());
				item.setTitle(temp.get("title").toString());
				item.setCreate_date(temp.get("create_date").toString());
				item.setDescription(temp.get("description").toString());
				item.setImage(temp.get("image").toString());
				item.setContent("content");
				list.add(item);

			}

		} catch (Exception ex) {
			////System.out.println(ex.getMessage());
		}
		return list;
	}
	public static ArrayList<Object> get_related_news(){
		String query = null;
		List<Map<String, Object>> listob = new ArrayList<Map<String, Object>>();
		ArrayList<Object> list = new ArrayList<Object>();
		try{			
			query = "SELECT writer_id,title,description,image,cate FROM tb_writer";
			IConnectEJBS con = new IConnectEJBS();
			listob = con.GetDataReturnResultSet(query);
			
			//get top 3 news
			 for(int i=0;i<3;i++){
				 Map<String, Object> temp=listob.get(i);
					WriterItem item = new WriterItem();
					item.setWriter_id(temp.get("writer_id").toString());
					item.setTitle(temp.get("title").toString());
					item.setDescription(temp.get("description").toString());
					item.setImage(temp.get("image").toString());
					item.setCate(temp.get("cate").toString());
			
					list.add(item);
			 }
		
			
		}catch(Exception ex){
			////System.out.println(ex.getMessage());
		}
		return list;
	}
	public static WriterItem get_news_id(String lang,
			String id) {
		WriterItem item = new WriterItem();
		String query;
		List<Map<String, Object>> listob = new ArrayList<Map<String, Object>>();

		try {
			query = "SELECT * FROM tb_writer where writer_id= '"+ id+"'";
					
			IConnectEJBS con = new IConnectEJBS();
			listob = con.GetDataReturnResultSet(query);
			Map<String, Object> temp = listob.get(0);				
			item.setWriter_id(temp.get("writer_id").toString());
			item.setTitle(temp.get("title").toString());				
			item.setImage(temp.get("image").toString());
			item.setCreate_date(temp.get("create_date").toString());
			item.setDescription(temp.get("description").toString());
			item.setContent(temp.get("content").toString());

		} catch (Exception ex) {
			////System.out.println(ex.getMessage());
		}
		return item;
	}
}
