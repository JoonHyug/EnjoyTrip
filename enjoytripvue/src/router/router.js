import Vue from "vue";
import VueRouter from "vue-router";
import ErrorPage from "@/components/Error";
import HomePage from "@/components/Home";
import DetailPage from "@/components/searchDetail/Detail";
import CardList from "@/components/views/CardList";
import KakaoMap from "@/components/views/KakaoMap";
import Login from "@/components/User/Login";
import ForgotPassword from "@/components/User/ForgotPassword";
import Register from "@/components/User/Register";
// Test
import mypage from "@/components/User/MyPage";
import BoardList from "@/components/board/BoardList";
import WriteCommunity from "@/components/board/WriteBoard";
import Board from "@/components/board/Board";
import BoardDetail from "@/components/board/BoardDetail";
import EditBoard from "@/components/board/EditBoard";
import TravelPlan from "@/components/travelPlan/makePlan";
import TrabelPlanBoard from "@/components/travelPlan/TravelPlanBoard";

Vue.use(VueRouter);

const routes = [
  { path: "/", component: HomePage },
  { path: "*", component: ErrorPage },
  { path: "/mypage", component: mypage, meta: { requiresAuth: true } },
  {
    path: "/detail/:id",
    name: "detail",
    component: DetailPage,
    meta: { requiresAuth: true },
  },
  { path: "/card_search", component: CardList, meta: { requiresAuth: true } },
  { path: "/map_search", component: KakaoMap, meta: { requiresAuth: true } },
  { path: "/login", component: Login },
  // /login/forgot_password로 하는게 나을듯 nested 가서 하기
  {
    path: "/forgot_password",
    component: ForgotPassword,
  },
  { path: "/register", component: Register },
  {
    path: "/mypagehome",
    name: "mypagehome",
    meta: { requiresAuth: true },
    component: () => import("@/components/User/MyPageHome"),
  },
  {
    path: "/mypagesetting",
    name: "mypagesetting",
    meta: { requiresAuth: true },
    component: () => import("@/components/User/MyPageSetting"),
  },
  {
    path: "/mypagebookmark",
    name: "mypagebookmark",
    meta: { requiresAuth: true },
    component: () => import("@/components/User/MyPageBookmark"),
  },
  {
    path: "/mypagereview",
    name: "mypagereview",
    meta: { requiresAuth: true },
    component: () => import("@/components/User/MyPageReview"),
  },
  {
    path: "/editreview/:id",
    name: "editreview",
    component: () => import("@/components/User/MyPageEditReview"),
  },
  {
    path: "/board",
    // 일반 게시판 컴포넌트
    component: Board,
    children: [
      {
        path: "write",
        component: WriteCommunity,
      },
      { path: "notice", component: BoardList },
      { path: "board", component: BoardList },
      {
        path: "detail/:id",
        component: BoardDetail,
        meta: { requiresAuth: true },
      },
      { path: "edit/:id", component: EditBoard, meta: { requiresAuth: true } },
    ],
  },
  { path: "/travelplan/:id", component: TravelPlan },
  { path: "/travelplan", component: TravelPlan, meta: { requiresAuth: true } },
  { path: "/travelboard", component: TrabelPlanBoard },
];

const router = new VueRouter({
  mode: "history",
  routes: routes,
});

router.beforeEach((to, from, next) => {
  if (to.meta.requiresAuth) {
    const isAuthenticated = JSON.parse(localStorage.getItem("token")).auth
      .token;
    // console.log(isAuthenticated);
    if (isAuthenticated) {
      next();
    } else {
      alert("로그인을 해주세요.");
      next("/login");
    }
  } else {
    next();
  }
});

export default router;
