import './styles/filter.css';
import {useState, useRef} from 'react';
import FilterTopics from './FilterTopics';
import KeywordFilter from './KeywordFilter';
import FilterExperience from './FilterExperience';

function Filter() {
    const [filterPanelShowing, setFilterPanelShowing] = useState(false);
    const [tags, setTags] = useState([]);
    const [chosenTopics] = useState([
        false, false, false, false, false, false, false, false,
        false, false, false, false, false, false, false, false
    ]);
    const [chosenExp] = useState([false, false, false, false]);
    const authorName = useRef();

    function showHideFilterPanel() {
        setFilterPanelShowing(!filterPanelShowing);
        if (filterPanelShowing) {
            document.getElementsByClassName('filter-arrow')[0].style = 'transform: rotate(-90deg);';
        }
        else {
            document.getElementsByClassName('filter-arrow')[0].style = 'transform: rotate(0deg);';
        }
    }

    return (
        <div className="filter-pane">
            <p className="filter-switch" onClick={() => showHideFilterPanel()}>
                <i className="fas fa-sort-down filter-arrow"></i> &nbsp;Filter
            </p>
            
            {(filterPanelShowing)?
              (<div className="filter-pane-elements">
                <FilterTopics chosenTopics={chosenTopics}/>
                <input type='text' placeholder='Author Username...' className='author-name' ref={authorName}></input>
                <FilterExperience chosenExp={chosenExp}/>
                <br></br>
                <KeywordFilter tags={tags} setTags={setTags}/>
                <button className='apply-filter-btn' onClick={() => {
                    let topicsChoices = chosenTopics.map((element, index) => {
                        if (element) return index;
                        else return -1;
                    });
                    topicsChoices = topicsChoices.filter((element) => {
                        return element !== -1;
                    });

                    let expChoices = chosenExp.map((element, index) => {
                        if (element) return index;
                        else return -1;
                    });
                    expChoices = expChoices.filter((element) => {
                        return element !== -1;
                    });

                    const searchBarVal = document.getElementsByClassName('search-bar-box')[0].value;
                    const buildLink = window.location.href.split('/');
                    const builtLink = `http://${buildLink[2]}/search-results?` +
                    `${(searchBarVal.trim().length === 0)?'':'q=' + searchBarVal}` +
                    `${(topicsChoices.length !== 0)?'&topics=' + topicsChoices:''}` +
                    `${(expChoices.length !== 0)?'&exp=' + expChoices:''}` + 
                    `${(authorName.current.value)?'&author=' + authorName.current.value:''}` +
                    `${(tags.length !== 0)?'&tags=' + tags.map((element) => element.trim()):''}`;
                    if (builtLink !== `http://${buildLink[2]}/search-results?`) {
                        window.location.href = builtLink;
                    }
                }}>Apply</button>
            </div>)
            :null}
        </div>
    );
};

export default Filter;

