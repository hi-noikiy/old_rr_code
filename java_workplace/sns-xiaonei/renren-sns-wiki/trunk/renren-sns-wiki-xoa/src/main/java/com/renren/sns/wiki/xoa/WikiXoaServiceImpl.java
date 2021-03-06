/**
 * Autogenerated by Thrift Compiler (0.8.0)
 *
 * DO NOT EDIT UNLESS YOU ARE SURE THAT YOU KNOW WHAT YOU ARE DOING
 *  @generated
 */
package com.renren.sns.wiki.xoa;

import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.renren.sns.wiki.adapter.WikiProfileAdapter;
import com.renren.sns.wiki.enums.InterestType;
import com.renren.sns.wiki.enums.WikiStateType;
import com.renren.sns.wiki.enums.WikiType;
import com.renren.sns.wiki.model.Wiki;
import com.renren.sns.wiki.service.UserActionRecordService;
import com.renren.sns.wiki.service.WikiService;
import com.renren.sns.wiki.utils.SpringBeanUtil;

public class WikiXoaServiceImpl extends WikiParserServiceImpl implements IWikiXoaService {

    private Log logger = LogFactory.getLog(WikiParserServiceImpl.class);

    /**
     * 获取切词服务代理
     */
    private UserActionRecordService getUserActionRecordService() {
        return SpringBeanUtil.getInstance().getBean(UserActionRecordService.class);
    }

    private WikiService getWikiService() {
        return SpringBeanUtil.getInstance().getBean(WikiService.class);
    }

    @Override
    public void deleteSimpleComment(int wikiId, int userId) {
        logger.warn("xoa deleteSimpleComment where wikiId=" + wikiId + ", userId=" + userId);
        UserActionRecordService userActionRecordService = getUserActionRecordService();
        userActionRecordService.deleteComment(wikiId, userId);
    }

    @Override
    public void asyncReloadResult(ParseContext context) {
        super.asyncReloadResult(context);
    }

    @Override
    public void asyncUpdateParseResult(ParseContext context, String privacy) {
        super.asyncUpdateParseResult(context, privacy);
    }

    @Override
    public ParseResult parseText(ParseContext context) {
        return super.parseText(context);
    }

    @Override
    public void asyncBatchReloadResult(List<ParseContext> contexts) {
        super.asyncBatchReloadResult(contexts);
    }

    @Override
    public boolean forbiddenWiki(int wikiId) {
        try {
            getWikiService().updateWikiState(wikiId, WikiStateType.FORBIDDEN);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    @Override
    public boolean pauseWiki(int wikiId) {
        try {
            getWikiService().updateWikiState(wikiId, WikiStateType.PAUSE);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    @Override
    public boolean reopenWiki(int wikiId) {
        try {
            getWikiService().updateWikiState(wikiId, WikiStateType.OPEN);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    /**
     * 获取Wiki状态
     * 
     * @param wikiId
     */
    @Override
    public WikiState getWikiState(int wikiId) {
        try {
            Wiki wiki = getWikiService().getWikiById(wikiId);
            WikiState state = WikiState.findByValue(wiki.getState());
            if (null == state) {
                logger.warn("null state find, return open state by default wikiId: " + wikiId);
                return WikiState.OPEN;
            }
            return state;
        } catch (Exception e) {
            logger.warn("exception happens return open state by default wikiId: " + wikiId);
            return WikiState.OPEN;
        }
    }

    /**
     * 获取个人主页展示wiki相关信息
     * 
     * @return
     */
    @Override
    public WikiProfile getWikiProfile(int userId) {
        WikiProfile wikiProfile = new WikiProfile();
        try {
            boolean isProfileUser = WikiProfileAdapter.getInstance().isProfileUser(userId);
            wikiProfile.setProfileUser(isProfileUser);
            if (isProfileUser) {
                List<Integer> wishMovieIdList = getUserActionRecordService().getWikiIdList(userId,
                        WikiType.MOVIE, InterestType.WISH);
                int wishMovieCount = wishMovieIdList == null ? 0 : wishMovieIdList.size();

                List<Integer> collectMovieIdList = getUserActionRecordService().getWikiIdList(
                        userId, WikiType.MOVIE, InterestType.COLLECT);
                int collectMovieCount = collectMovieIdList == null ? 0 : collectMovieIdList.size();

                wikiProfile.setWishMovieCount(wishMovieCount);
                wikiProfile.setCollectMovieCount(collectMovieCount);
            }
        } catch (Exception e) {
            logger.warn("exception happens return getWikiProfile by userId: " + userId);
        }
        return wikiProfile;
    }

}
