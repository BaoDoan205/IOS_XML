package IOS_XML;

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

public class Bai2_XML {
    public static void main(String[] args) {
        ArrayList<Student> studentList = new ArrayList<>();
        studentList.add(new Student("Doan Bao", 20, 3.8));
        studentList.add(new Student("Huy Vuong", 21, 3.9));
        studentList.add(new Student("Duy Thang", 19, 3.5));

        try {
            writeToXML(studentList, "student_list.xml");
            System.out.println("Danh sách sinh viên đã được ghi vào file student_list.xml");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static void writeToXML(ArrayList<Student> studentList, String fileName)
            throws ParserConfigurationException, TransformerException, IOException, SAXException {
        DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
        DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
        Document doc = dBuilder.newDocument();

        Element rootElement = doc.createElement("students");
        doc.appendChild(rootElement);

        for (Student student : studentList) {
            Element studentElement = doc.createElement("student");
            rootElement.appendChild(studentElement);

            Element nameElement = doc.createElement("name");
            nameElement.appendChild(doc.createTextNode(student.getName()));
            studentElement.appendChild(nameElement);

            Element ageElement = doc.createElement("age");
            ageElement.appendChild(doc.createTextNode(String.valueOf(student.getAge())));
            studentElement.appendChild(ageElement);

            Element gpaElement = doc.createElement("gpa");
            gpaElement.appendChild(doc.createTextNode(String.valueOf(student.getGpa())));
            studentElement.appendChild(gpaElement);
        }

        TransformerFactory transformerFactory = TransformerFactory.newInstance();
        Transformer transformer = transformerFactory.newTransformer();
        transformer.setOutputProperty(OutputKeys.INDENT, "yes");
        DOMSource source = new DOMSource(doc);
        StreamResult result = new StreamResult(new FileWriter(fileName));
        transformer.transform(source, result);
    }
}

class Student {
    private String name;
    private int age;
    private double gpa;

    public Student(String name, int age, double gpa) {
        this.name = name;
        this.age = age;
        this.gpa = gpa;
    }

    public String getName() {
        return name;
    }

    public int getAge() {
        return age;
    }

    public double getGpa() {
        return gpa;
    }
}
