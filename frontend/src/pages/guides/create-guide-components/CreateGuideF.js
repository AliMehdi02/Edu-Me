import "./create-guide.css";
import { useRef, useState, useEffect } from "react";
import TopicDropdown from "./TopicDropdown";
import CodeMirror from "@uiw/react-codemirror";
import { markdown, markdownLanguage } from "@codemirror/lang-markdown";
import { languages } from "@codemirror/language-data";
import MarkdownPreview from "@uiw/react-markdown-preview";
import { useNavigate, useLocation } from "react-router-dom";
import { Button, Image } from "react-bootstrap";
import AddTags from "./AddTags";
import { idToTopic, idToExp } from "./../../../utils/Utils";
import Navbarr from "../../Navbar/Indexx";

const CreateGuideF = () => {
  const { state } = useLocation();
  let navigate = useNavigate();
  const [guideIntro, setGuideIntro] = useState({});

  const [code, setCode] = useState("\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n");
  const [mdSource, setMdSource] = useState("");

  const [selectedTopic, setSelectedTopic] = useState("Pick a topic...");

  const [showError, setShowError] = useState(false);
  const [titleTooLongError, setTitleTooLongError] = useState(false);
  const [descTooLongError, setDescTooLongError] = useState(false);
  const [tooLongTagError, setTooLongTagError] = useState(false);
  const [tooManyTagsError, setTooManyTagsError] = useState(false);

  const handleChange = (e) => {
    setGuideIntro({ ...guideIntro, [e.target.name]: e.target.value });
  };

  const [tags, setTags] = useState([]);

  const validateGuide = (guideData) => {
    // Test for length
    if (guideData.title.length > 60) {
      setTitleTooLongError(true);
    } else setTitleTooLongError(false);
    if (guideData.description.length > 750) {
      setDescTooLongError(true);
    } else setDescTooLongError(false);
    if (tags.length > 10) {
      setTooManyTagsError(true);
      return false;
    } else setTooManyTagsError(false);
    for (let tag of tags) {
      if (tag.length > 15) {
        setTooLongTagError(true);
        return false;
      } else setTooLongTagError(false);
    }
    // Test for blanks
    if (
      guideData.title.trim().length === 0 ||
      guideData.content.trim().length === 0 ||
      guideData.topicId.includes("Pick a topic...")
    ) {
      console.error("wrong inputs");
      setShowError(true);
      return false;
    }

    setShowError(false);
    return true;
  };

  async function submitGuide(guideData) {
    if (!validateGuide(guideData)) return;

    guideData.topicId = topicToId(guideData.topicId);
    guideData.expLvl = expToId(guideData.expLvl);
    const uId = localStorage.getItem("UsersID from user table");
    let dataToSend = null;
    console.log("passed");
    const token = "Bearer " + localStorage.getItem("token");

    if (state) {
      dataToSend =  [{ ...guideData, userId: parseInt(uId), sourceId: state[0].sourceId }, tags];
    } 
    else {
      dataToSend =  [{ ...guideData, userId: parseInt(uId), sourceId: parseInt(uId) }, tags];
    }

    const res = await fetch("http://localhost:8080/submit-new-guide", {
      method: "POST",
      headers: {
        "Content-type": "application/json",
        Authorization: token,
      },
      body: JSON.stringify(dataToSend),
    }).catch((err) => {
      console.error(err);
    });
    console.log(res.json());

    navigate("/guides");
  }

  const topicToId = (topicName) => {
    switch (topicName) {
      case "Computer Architecture":
        return 0;
      case "Operating Systems":
        return 1;
      case "Standard Coding":
        return 2;
      case "Discrete Maths":
        return 3;
      case "Algorithms":
        return 4;
      case "Data Structures":
        return 5;
      case "Compilers":
        return 6;
      case "Interpreters":
        return 7;
      case "Databases":
        return 8;
      case "Networks":
        return 9;
      case "Design Patterns":
        return 10;
      case "Web Development":
        return 11;
      case "Artificial Intelligence":
        return 12;
      case "Machine Learning":
        return 13;
      case "Deep Learning":
        return 14;
      case "Cybersecurity":
        return 15;
      default:
        return -1;
    }
  };

  const expToId = (expLevel) => {
    switch (expLevel) {
      case "Novice":
        return 0;
      case "Beginner":
        return 1;
      case "Intermediate":
        return 2;
      case "Advanced":
        return 3;
      default:
        return -1;
    }
  };

  
  useEffect(() => {
    if (state) {
      setGuideIntro({
        title: state[0].title,
        description: state[0].description,
      });
      setCode(state[0].content);
      setSelectedTopic(
        `${idToTopic(state[0].topicId)} - ${idToExp(state[0].expLvl)}`
      );
      setTags(state[1]);
    }
  }, []);

  return (
    <>
     <Navbarr/>
      <div className="parent-container">
        <div className="main-content">
          <h1 className="create-headline">
            {state ? "Fork a Guide" : "Create a new Study Guide"}
          </h1>

          <input
            type="text"
            name="title"
            value={guideIntro?.title}
            onChange={handleChange}
            placeholder="Add a title..."
            className="titlebox"
          ></input>

          <textarea
            name="description"
            value={guideIntro?.description}
            onChange={handleChange}
            className="desc-box"
            placeholder="Add a description..."
          ></textarea>

          <TopicDropdown
            selected={selectedTopic}
            setSelected={setSelectedTopic}
          />

          <AddTags tags={tags} setTags={setTags} />
          <div className="codemirror-box">
            <h3>Content:</h3>
            <CodeMirror
              value={code}
              extensions={[
                markdown({ base: markdownLanguage, codeLanguages: languages }),
              ]}
              theme="light"
              className="codemirror-editor"
              minHeight="20rem"
              onChange={(value) => setCode(value)}
            />
          </div>

          {mdSource.trim().length === 0 ? null : (
            <div className="preview-box">
              <MarkdownPreview source={mdSource} />
            </div>
          )}

          {showError ? (
            <p className="error-message">
              ERROR: Please enter a title and some content and pick a relevant
              topic.
            </p>
          ) : null}
          {titleTooLongError ? (
            <p className="error-message">
              ERROR: Title too long; Guide title should be less than 60
              characters.
            </p>
          ) : null}
          {descTooLongError ? (
            <p className="error-message">
              ERROR: Description too long, please limit to 750 characters.
            </p>
          ) : null}
          {tooLongTagError ? (
            <p className="new-guide-error-message">
              ERROR: Please limit each tag to 15 characters in length.
            </p>
          ) : null}
          {tooManyTagsError ? (
            <p className="new-guide-error-message">
              ERROR: Please select 10 or fewer tags.
            </p>
          ) : null}

          <div className="buttons-box">
            <button className="preview-btn" onClick={() => setMdSource(code)}>
              Preview
            </button>

            <button
              className="submit-btn"
              onClick={async () => {
                const [topicChosen, expChosen] = selectedTopic.split(" - ");
                const toPrint2 = [topicChosen, expChosen, code];
                toPrint2.forEach((item) => console.log(item));
                const guideData = {
                  title: guideIntro.title,
                  content: code,
                  description: guideIntro.description,
                  topicId: topicChosen,
                  expLvl: expChosen,
                };
                submitGuide(guideData);
              }}
            >
              Submit
            </button>
          </div>
        </div>
      </div>
    </>
  );
};

export default CreateGuideF;
